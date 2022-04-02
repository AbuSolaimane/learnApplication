package com.fadili.learn.security;

import com.fadili.learn.SpringApplicationContext;

public class SecurityConstants {
	
	public static final long EXPIRATION_TIME = 864000000;
	public static final String TOKEN_PEFIX ="Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	
	public static String getTokenSecret() {
		
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
		return appProperties.getTokenSecret();
	}
	

}
