package com.deloitte.bu.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.bu.models.SearchDto;
import com.deloitte.bu.models.User;
import com.deloitte.bu.models.UserAttributes;
import com.deloitte.bu.repository.UserLdapRepository;

@Component
public class Filters {
	
	@Autowired
	UserLdapRepository userRepository;
	
	public List<User> exactFilter(UserAttributes request) throws Exception{
		List<User> users = new ArrayList<User>();
		
		// Exact Filter 1
		SearchDto exact_filter1 = new SearchDto();
		exact_filter1.setPersonFirstName(request.getPersonFirstName());
		exact_filter1.setPersonLastName(request.getPersonLastName());
		exact_filter1.setPersonDateOfBirth(request.getPersonDateOfBirth());
		exact_filter1.setPersonEmail(request.getPersonEmail());
		users.addAll(userRepository.find(exact_filter1));
		
		if(request.getPersonSsn() != null &&  request.getPersonSsn() != "") {
			// Exact Filter 2
			SearchDto exact_filter2 = new SearchDto();
			exact_filter2.setPersonFirstName(request.getPersonFirstName());
			exact_filter2.setPersonLastName(request.getPersonLastName());
			exact_filter2.setPersonDateOfBirth(request.getPersonDateOfBirth());
			exact_filter2.setPersonSsn(request.getPersonSsn());
			users.addAll(userRepository.find(exact_filter2));
			
			// Exact Filter 3
			SearchDto exact_filter3 = new SearchDto();
			exact_filter3.setPersonFirstName(request.getPersonFirstName());
			exact_filter3.setPersonLastName(request.getPersonLastName());
			exact_filter3.setPersonEmail(request.getPersonEmail());
			exact_filter3.setPersonSsn(request.getPersonSsn());
			users.addAll(userRepository.find(exact_filter3));
			
			// Exact Filter 4
			SearchDto exact_filter4 = new SearchDto();
			exact_filter4.setPersonFirstName(request.getPersonFirstName());
			exact_filter4.setPersonDateOfBirth(request.getPersonDateOfBirth());
			exact_filter4.setPersonEmail(request.getPersonEmail());
			exact_filter4.setPersonSsn(request.getPersonSsn());
			users.addAll(userRepository.find(exact_filter4));
			
			// Exact Filter 5
			SearchDto exact_filter5 = new SearchDto();
			exact_filter5.setPersonLastName(request.getPersonLastName());
			exact_filter5.setPersonDateOfBirth(request.getPersonDateOfBirth());
			exact_filter5.setPersonEmail(request.getPersonEmail());
			exact_filter5.setPersonSsn(request.getPersonSsn());
			users.addAll(userRepository.find(exact_filter5));
		}
		
		if(request.getPersonPassport() != null &&  request.getPersonPassport() != "") {
			// Exact Filter 6
			SearchDto exact_filter6 = new SearchDto();
			exact_filter6.setPersonFirstName(request.getPersonFirstName());
			exact_filter6.setPersonLastName(request.getPersonLastName());
			exact_filter6.setPersonDateOfBirth(request.getPersonDateOfBirth());
			exact_filter6.setPersonSsn(request.getPersonPassport());
			users.addAll(userRepository.find(exact_filter6));
			
			// Exact Filter 7
			SearchDto exact_filter7 = new SearchDto();
			exact_filter7.setPersonFirstName(request.getPersonFirstName());
			exact_filter7.setPersonLastName(request.getPersonLastName());
			exact_filter7.setPersonEmail(request.getPersonEmail());
			exact_filter7.setPersonSsn(request.getPersonPassport());
			users.addAll(userRepository.find(exact_filter7));
			
			// Exact Filter 8
			SearchDto exact_filter8 = new SearchDto();
			exact_filter8.setPersonFirstName(request.getPersonFirstName());
			exact_filter8.setPersonDateOfBirth(request.getPersonDateOfBirth());
			exact_filter8.setPersonEmail(request.getPersonEmail());
			exact_filter8.setPersonSsn(request.getPersonPassport());
			users.addAll(userRepository.find(exact_filter8));
			
			// Exact Filter 9
			SearchDto exact_filter9 = new SearchDto();
			exact_filter9.setPersonLastName(request.getPersonLastName());
			exact_filter9.setPersonDateOfBirth(request.getPersonDateOfBirth());
			exact_filter9.setPersonEmail(request.getPersonEmail());
			exact_filter9.setPersonSsn(request.getPersonPassport());
			users.addAll(userRepository.find(exact_filter9));
		}
		
		// remove duplicates
		Set<User> distict_users = new HashSet<User>(users);
		users.clear();
		users.addAll(distict_users);
		return users;
	}
	
	public List<User> likelyFilter(UserAttributes  request) throws Exception{
		List<User> users = new ArrayList<User>();
		
		// Likely Filter 1
		SearchDto likely_filter1 = new SearchDto();
		likely_filter1.setPersonDateOfBirth(request.getPersonDateOfBirth());
		likely_filter1.setPersonEmail(request.getPersonEmail());
		users.addAll(userRepository.find(likely_filter1));
		
		if(request.getPersonSsn() != null &&  request.getPersonSsn() != "") {
			// Likely Filter 2
			SearchDto likely_filter2 = new SearchDto();
			likely_filter2.setPersonDateOfBirth(request.getPersonDateOfBirth());
			likely_filter2.setPersonSsn(request.getPersonSsn());
			users.addAll(userRepository.find(likely_filter2));
		}
		
		if(request.getPersonPassport() != null &&  request.getPersonPassport() != "") {
			// Likely Filter 3
			SearchDto likely_filter3 = new SearchDto();
			likely_filter3.setPersonDateOfBirth(request.getPersonDateOfBirth());
			likely_filter3.setPersonPassport(request.getPersonPassport());
			users.addAll(userRepository.find(likely_filter3));
		}
		
		// remove duplicates
		Set<User> distict_users = new HashSet<User>(users);
		users.clear();
		users.addAll(distict_users);
		return users;
	}
	
	public List<User> potentialFilter(UserAttributes  request) throws Exception{
		List<User> users = new ArrayList<User>();
		
		// Potential Filter 1
		SearchDto potential_filter1 = new SearchDto();
		potential_filter1.setPersonDateOfBirth(request.getPersonDateOfBirth());
		potential_filter1.setPersonLastName(request.getPersonLastName());
		users.addAll(userRepository.find(potential_filter1));
		
		// Potential Filter 2
		SearchDto potential_filter2 = new SearchDto();
		potential_filter2.setPersonEmail(request.getPersonEmail());
		users.addAll(userRepository.find(potential_filter2));
				
		if(request.getPersonSsn() != null &&  request.getPersonSsn() != "") {
			// Potential Filter 3
			SearchDto potential_filter3 = new SearchDto();
			potential_filter3.setPersonSsn(request.getPersonSsn());
			users.addAll(userRepository.find(potential_filter3));
		}
		
		if(request.getPersonPassport() != null &&  request.getPersonPassport() != "") {
			// Potential Filter 4
			SearchDto potential_filter4 = new SearchDto();
			potential_filter4.setPersonPassport(request.getPersonPassport());
			users.addAll(userRepository.find(potential_filter4));
		}
		
		// remove duplicates
		Set<User> distict_users = new HashSet<User>(users);
		users.clear();
		users.addAll(distict_users);
		return users;
	}
	
}
