package com.fadili.learn.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class AddressDto extends RepresentationModel<AddressDto> {
	
	private String addressId;
	private String city;
	private String country;
	private String streetName;
	private String postalCode;
	private String type;
	private UserAppDto userAppDto;

}
