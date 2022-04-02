package com.fadili.learn.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHANUMERIC = "0123456789QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm";
	
	public String generateRandomKey(int length) {
		
		StringBuilder builder = new StringBuilder(length);
		for(int i=0; i<length; i++) {
			builder.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
		}

		return new String(builder);
	}

}
