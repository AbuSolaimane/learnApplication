package com.fadili.learn.services;

import java.util.List;

import com.fadili.learn.dto.AddressDto;

public interface AddressService {
	
	List<AddressDto> getAddressesByUser(String userID);

	AddressDto getAddressesByIdAndByUser(String userID, String addressID);

}
