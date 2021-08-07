package com.deloitte.bu.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GenerateBuid {
	public String generateBuid() {
		
		Random rand = new Random();
		
        int sevenDigitBUID = rand.nextInt(9999999);
        
        String sevenDigits = appendTrailingZeros(sevenDigitBUID);
        
        int sum = getSumOfDigitsInANumber(sevenDigitBUID);
       
        int secondDigit = Math.floorMod((10-sum),10);
        
        String buid = "U" + String.valueOf(secondDigit) + sevenDigits;
        
		return buid;
	}

	private String appendTrailingZeros(int sevenDigitBUID) {
		String id = String.valueOf(sevenDigitBUID);
		
		String buid = "";
		
		int n_trailing_zeros = 7 - id.length();
		
		for(int i=0;i<n_trailing_zeros;i++) {
			buid += '0';
		}
		
		buid += id;
		
		return buid;
	}
	
	 private int getSumOfDigitsInANumber(int num){
		 int sum = 0;
		 
		 while (num > 0) {
			 sum = sum + num % 10;
			 num = num / 10;
		 }
		 
		 return sum;
	 }
}
