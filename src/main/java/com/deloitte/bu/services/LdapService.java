package com.deloitte.bu.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.deloitte.bu.exception.RaiseException;
import com.deloitte.bu.models.AuditLog;
import com.deloitte.bu.models.MatchLog;
import com.deloitte.bu.models.ReplenishmentLog;
import com.deloitte.bu.models.Replenishment;
import com.deloitte.bu.models.ReplenishmentMessage;
import com.deloitte.bu.models.RequestDto;
import com.deloitte.bu.models.ResponseDto;
import com.deloitte.bu.models.UserAttributes;
import com.deloitte.bu.repository.UserLdapRepository;
import com.deloitte.bu.utils.Filters;
import com.deloitte.bu.utils.GenerateBuid;
import com.deloitte.bu.utils.LogToEC2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.deloitte.bu.models.User;

@Service
public class LdapService {

	private final Logger logger = LoggerFactory.getLogger(LdapService.class);
	private final Logger auditLogger = LoggerFactory.getLogger("audit");
	private final Logger matchLogger = LoggerFactory.getLogger("match");
	private final Logger replLogger = LoggerFactory.getLogger("replenishment");
	boolean replStatus=false;
	//int replRetryCount=0;
	
	@Autowired
	UserLdapRepository userRepository;
	
	@Autowired 
	Validator validator;
	
	@Autowired
	LogToEC2 remoteLogger;
	
	@Autowired
	private Filters filter;

	@Autowired
	private GenerateBuid generator;
	
	@Autowired 
	private RaiseException exceptions;
	
	@Value("${replenishmentUrl}")
	private String replenishmentUrl;
	
	@Value("${apiToken}")
	private String apiToken;

	public ResponseDto getBuid(RequestDto request) {
		logger.debug("getBuid Service");
		Set<ConstraintViolation<RequestDto>> violations = validator.validate(request);
		
		if (violations != null && !violations.isEmpty()) {
			List<String> errors = new ArrayList<String>();
			violations.forEach((v) -> {
				errors.add(v.getMessage());
			});
			return exceptions.raiseValidationErrors(errors);
		}
		
		String sor = request.getSor();
		UserAttributes userDetails = request.getAttributes();
		List<Replenishment> replenishments = request.getRepl();

		List<User> users = new ArrayList<User>();

		String return_status_code = null;
		String return_status_description = null;
		String buid_created = null;
		
		List<User> exactMatchUsers = null;
		try {
			exactMatchUsers = filter.exactFilter(userDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptions.raiseException(e.getMessage());
		}
		int exactMatchCount = exactMatchUsers.size();
		if (exactMatchCount == 1) {
			User user = null;
			try {
				user = generateBuidandCreateUser(userDetails, sor);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return exceptions.raiseException(e.getMessage());
			}
			buid_created = user.getBuid();
			users = exactMatchUsers;
			return_status_code = "0";
			return_status_description = "Successful. Created new buid.";
			
			matchLogger.info(createMatchLog("Exact", users, buid_created));
			String repln = getReplenishmentPayload(sor, user, replenishments);
			logger.debug(repln);
			
			sendOrRetryReplenishment(repln);
//			try {
//				remoteLogger.log(createMatchLog("Exact", users, null), "match");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				return exceptions.raiseException(e.getMessage());
//			}
			
			ResponseDto response = new ResponseDto(
					null, 
					return_status_code, 
					return_status_description,
					buid_created
				);
			return response;
		} else if (exactMatchCount > 1) {
			User user = null;
			try {
				user = generateBuidandCreateUser(userDetails, sor);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return exceptions.raiseException(e.getMessage());
			}
			buid_created = user.getBuid();
			users = exactMatchUsers;
			return_status_code = "0";
			return_status_description = "Successful. Created new buid.";
			
			matchLogger.info(createMatchLog("Multiple", users, user.getBuid()));
//			try {
//				remoteLogger.log(createMatchLog("Multiple", users, user.getBuid()), "match");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				return exceptions.raiseException(e.getMessage());
//			}
			
			String repln = getReplenishmentPayload(sor, user, replenishments);
			logger.debug(repln);

			sendOrRetryReplenishment(repln);
//			try {
//				replStatus = sendReplenishment(repln);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			if(!replStatus)
			{
				replLogger.info(createReplLog(user, repln));
			}

			ResponseDto response = new ResponseDto(
					null, 
					return_status_code, 
					return_status_description,
					buid_created
				);
			return response;
		}

		List<User> likelyMatchUsers = null;
		try {
			likelyMatchUsers = filter.likelyFilter(userDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptions.raiseException(e.getMessage());
		}
		int likelyMatchCount = likelyMatchUsers.size();
		if (likelyMatchCount == 1) {
			User user = null;
			try {
				user = generateBuidandCreateUser(userDetails, sor);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return exceptions.raiseException(e.getMessage());
			}
			buid_created = user.getBuid();
			users = likelyMatchUsers;
			return_status_code = "0";
			return_status_description = "Successful. Created new buid.";
			
			matchLogger.info(createMatchLog("Likely", users, user.getBuid()));
//			try {
//				remoteLogger.log(createMatchLog("Likely", users, user.getBuid()), "match");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				return exceptions.raiseException(e.getMessage());
//			}
			
			String repln = getReplenishmentPayload(sor, user, replenishments);
			logger.debug(repln);

			sendOrRetryReplenishment(repln);
			
//			try {
//				sendReplenishment(repln);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			if(!replStatus)
			{
				replLogger.info(createReplLog(user, repln));
			}

			ResponseDto response = new ResponseDto(
					null, 
					return_status_code, 
					return_status_description,
					buid_created
				);
			return response;
		} else if (likelyMatchCount > 1) {
			User user = null;
			try {
				user = generateBuidandCreateUser(userDetails, sor);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return exceptions.raiseException(e.getMessage());
			}
			buid_created = user.getBuid();
			users = likelyMatchUsers;
			return_status_code = "0";
			return_status_description = "Successful. Created new buid.";
			
			matchLogger.info(createMatchLog("Multiple", users, user.getBuid()));
//			try {
//				remoteLogger.log(createMatchLog("Multiple", users, user.getBuid()), "match");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				return exceptions.raiseException(e.getMessage());
//			}
			
			String repln = getReplenishmentPayload(sor, user, replenishments);
			logger.debug(repln);
			
			sendOrRetryReplenishment(repln);
			
//			try {
//				sendReplenishment(repln);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			if(!replStatus)
			{
				replLogger.info(createReplLog(user, repln));
			}
			
			ResponseDto response = new ResponseDto(
					null, 
					return_status_code, 
					return_status_description,
					buid_created
				);
			return response;
		}

		List<User> potentialMatchUsers = null;
		try {
			potentialMatchUsers = filter.potentialFilter(userDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptions.raiseException(e.getMessage());
		}
		int potentialMatchCount = potentialMatchUsers.size();
		if (potentialMatchCount == 1) {
			User user = null;
			try {
				user = generateBuidandCreateUser(userDetails, sor);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return exceptions.raiseException(e.getMessage());
			}
			buid_created = user.getBuid();
			users = potentialMatchUsers;
			return_status_code = "0";
			return_status_description = "Successful. Created new buid.";
			
			matchLogger.info(createMatchLog("Potential", users, user.getBuid()));
//			try {
//				remoteLogger.log(createMatchLog("Potential", users, user.getBuid()), "match");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				return exceptions.raiseException(e.getMessage());
//			}
			
			String repln = getReplenishmentPayload(sor, user, replenishments);
			logger.debug(repln);
			
			sendOrRetryReplenishment(repln);
			
//			try {
//				sendReplenishment(repln);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
			if(!replStatus)
			{
				replLogger.info(createReplLog(user, repln));
			}
			
			ResponseDto response = new ResponseDto(
					null, 
					return_status_code, 
					return_status_description,
					buid_created
				);
			return response;
		} else if (potentialMatchCount > 1) {
			User user = null;
			try {
				user = generateBuidandCreateUser(userDetails, sor);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return exceptions.raiseException(e.getMessage());
			}
			buid_created = user.getBuid();
			users = potentialMatchUsers;
			return_status_code = "0";
			return_status_description = "Successful. Created new buid.";
			
			matchLogger.info(createMatchLog("Multiple", users, user.getBuid()));
//			try {
//				remoteLogger.log(createMatchLog("Multiple", users, user.getBuid()), "match");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				return exceptions.raiseException(e.getMessage());
//			}
			
			String repln = getReplenishmentPayload(sor, user, replenishments);
			logger.debug(repln);
			
			sendOrRetryReplenishment(repln);
			
//			try {
//				sendReplenishment(repln);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			if(!replStatus)
			{
				replLogger.info(createReplLog(user, repln));
			}
			
			ResponseDto response = new ResponseDto(
					null, 
					return_status_code, 
					return_status_description,
					buid_created
				);
			return response;
		}

		User user = null;
		try {
			user = generateBuidandCreateUser(userDetails, sor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptions.raiseException(e.getMessage());
		}
		buid_created = user.getBuid();
		return_status_code = "0";
		return_status_description = "Successful. Created new buid.";
		
		matchLogger.info(createMatchLog("No Match", users, user.getBuid()));
//		try {
//			remoteLogger.log(createMatchLog("No Match", users, user.getBuid()), "match");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			return exceptions.raiseException(e.getMessage());
//		}
		
		String repln = getReplenishmentPayload(sor, user, replenishments);
		logger.debug(repln);
		
		sendOrRetryReplenishment(repln);
		
//		try {
//			sendReplenishment(repln);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		if(!replStatus)
		{
			replLogger.info(createReplLog(user, repln));
		}
		
		ResponseDto response = new ResponseDto(
				null, 
				return_status_code, 
				return_status_description,
				buid_created
			);
		return response;
	}

	private User generateBuidandCreateUser(UserAttributes request, String createdBy) throws Exception{
		
		logger.debug("In method Generate BUID");
		boolean buidAlreadyExists = true;
		String buid =null;
		do {
		buid = generator.generateBuid();
		logger.debug("BUID generated -"+buid);
		User searcheduserAttributes = null;
		try {
			searcheduserAttributes = userRepository.findByPrimaryKey(buid);
			logger.debug("searched user attr - "+searcheduserAttributes.toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//return exceptions.raiseException(e1.getMessage());
		}
		//String searcheduserBUID = searcheduserAttributes.getBuid();
		if(searcheduserAttributes == null)
			buidAlreadyExists = false;
		}while(buidAlreadyExists);
		

		Date date = Calendar.getInstance().getTime();
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hhmmss.SSSSSS");
		String createdOn = formatter.format(date);

		User user = new User(buid, request.getPersonFirstName(), request.getPersonLastName(),
				request.getPersonDateOfBirth(), request.getPersonEmail(), request.getPersonSsn(),
				request.getPersonPassport(), createdOn, createdBy, null, null);
		user.setPersonMiddleName(request.getPersonMiddleName());
		user.setPersonNamePrefix(request.getPersonNamePrefix());
		user.setPersonNameSuffix(request.getPersonNameSuffix());
		userRepository.create(user);
		logger.debug("Creating Audit Log");
		auditLogger.info(createLog(user));
		//remoteLogger.log(createLog(user), "audit");
		return user;
	}

	private String createLog(User user) {
		AuditLog item = new AuditLog(user.getBuid(), user.getCreatedOn() ,user.getCreatedBy(), user.getModifiedOn(), user.getModifiedBy());
		return item.toString();
	}
	
	private String createReplLog(User user, String repln) {
		ReplenishmentLog item = new ReplenishmentLog(user.getBuid(), user.getCreatedOn() ,user.getCreatedBy(), repln);
		return item.toString();
	}

	private String getReplenishmentPayload(String sor, User user, List<Replenishment> replenishments) {
		ReplenishmentMessage msg = mapReplnMessage(sor, user, replenishments);
		ObjectMapper mapper = new ObjectMapper();
		String repln = null;
		try {
			repln = mapper.writeValueAsString(msg);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String finalRepln = "{\"message\" : "+repln+"}";
		return finalRepln;
	}

	private ReplenishmentMessage mapReplnMessage(String sor, User userDetails, List<Replenishment> repln) {
		ReplenishmentMessage msg = new ReplenishmentMessage();
		msg.setSor(sor);
		msg.setBuid(userDetails.getBuid());
		msg.setPersonFirstName(userDetails.getPersonFirstName());
		msg.setPersonMiddleName(userDetails.getPersonMiddleName());
		msg.setPersonNamePrefix(userDetails.getPersonNamePrefix());
		msg.setPersonNameSuffix(userDetails.getPersonNameSuffix());
		msg.setPersonLastName(userDetails.getPersonLastName());
		msg.setPersonDateOfBirth(userDetails.getPersonDateOfBirth());
		msg.setPersonEmail(userDetails.getPersonEmail());
		msg.setPersonSsn(userDetails.getPersonSsn());
		msg.setPersonPassport(userDetails.getPersonPassport());
		msg.setCreatedOn(userDetails.getCreatedOn());
		msg.setCreatedBy(userDetails.getCreatedBy());
		msg.setUpdatedOn(userDetails.getModifiedOn());
		msg.setUpdatedBy(userDetails.getModifiedBy());
		msg.setRepln(repln);
		return msg;
	}

	private boolean sendReplenishment(String payload) throws IOException {
		logger.debug("Sending Replenishment");
		URL url = new URL(replenishmentUrl);
		URLConnection con = url.openConnection();
		HttpURLConnection http = (HttpURLConnection) con;
		http.setRequestMethod("POST"); // PUT is another valid option
		http.setDoOutput(true);
		byte[] out = payload.getBytes(StandardCharsets.UTF_8);
		int length = out.length;

		http.setFixedLengthStreamingMode(length);
		http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		//http.setRequestProperty("x-api-key", "bXcrgKpJDV7EvfeUQMXwaaWJ3sao6coL9eLE");
		http.setRequestProperty("x-api-key", apiToken);
		http.connect();
		try (OutputStream os = http.getOutputStream()) {
			os.write(out);
		}
		int responseCode = ((HttpURLConnection) con).getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			logger.debug("Replenishment Sent");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
			return true;
			
		}
		logger.debug("Replenishment sending failed");
		return false;
	}
	
	private boolean sendOrRetryReplenishment(String repln)
	{
		replStatus=false;
		for(int i=0; i<=2; i++ )
			{	
				try {
					  replStatus = sendReplenishment(repln);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.debug("Got an exception while sending Replenishment, and Replenishment Status value is - "+replStatus);
						replStatus=false;
					}
			
				if (replStatus)
					  break;
				else {
					  	if(i<2)
					  	{
					  		try {
						  		logger.debug(" Replenishment Failed. Retrying Sending Replenishment after 5 seconds");
								TimeUnit.SECONDS.sleep(5);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					  	}
				  	 }
			}
		return replStatus;
	}
	
	private List<String> getBuidsList(List<User> users){
		List<String> buids = new ArrayList<>();
		for(User user: users) {
			buids.add(user.getBuid());
		}
		if(buids.isEmpty()) {
			return null;
		}
		return buids;
	}
	
	private String createMatchLog(String matchType, List<User> users, String buid_created) {
		MatchLog item = new MatchLog(matchType, getBuidsList(users), buid_created);
		return item.toString();
	}
}
