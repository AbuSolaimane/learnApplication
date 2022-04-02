package com.fadili.learn.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fadili.learn.SpringApplicationContext;
import com.fadili.learn.dto.LoginRequestDto;
import com.fadili.learn.models.UserApp;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		LoginRequestDto loginRequestDto = null;

		try {
			loginRequestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}


		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginRequestDto.getEmail(), loginRequestDto.getPassword(), new ArrayList<>());
		
		Authentication auth = authenticationManager.authenticate(authenticationToken);

		return auth;

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String username = ((UserAppDetails) authResult.getPrincipal()).getUsername();

		String token = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();
		
		UserAppDetailsServiceImpl userAppDetailsServiceImpl = (UserAppDetailsServiceImpl) SpringApplicationContext.getBean("userAppDetailsServiceImpl");
		UserAppDetails userAppDetails = (UserAppDetails) userAppDetailsServiceImpl.loadUserByUsername(username);
		UserApp userApp = userAppDetails.getUserApp();

		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PEFIX + token);
		response.addHeader("UserID" , userApp.getUserId());
	}

}
