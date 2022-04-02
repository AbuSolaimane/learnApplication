package com.fadili.learn.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserAppDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1032530516998839388L;
	
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String confirmedPassword;
	private List<AddressDto> addresses;

}
