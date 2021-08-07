package com.deloitte.bu.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

@Component
public class LogToEC2 {
	private String userName = "Administrator";
	private String password = "BDs93qGyBcYvnulTETBFT4$XXoSrPS*W";
	private String domain = null;
	private String auditFileName = "audit.txt";
	private String matchFileName = "match.txt";
	private String sharedPath = "smb://65.0.132.144/C$/tmp/";
	
	public void log(String data, String type) throws MalformedURLException, SmbException, UnknownHostException, IOException {
	    NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(domain, userName, password);	    
	    String filename="log.txt";
	    if(type=="audit") filename=auditFileName;
	    if(type=="match") filename=matchFileName;
	    String url = sharedPath + filename;
	    
	    // Append Data
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timestamp = formatter.format(date);
		String contentToAppend = timestamp + ", " + data + "\n";
	    try (OutputStream out = new SmbFileOutputStream(new SmbFile(url, authentication), true)) {
	        byte[] bytesToWrite = contentToAppend.getBytes();
	        if (out != null && bytesToWrite != null && bytesToWrite.length > 0) {
	            out.write(bytesToWrite);
	        }
	    }
	}
}
