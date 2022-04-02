package com.fadili.learn.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fadili.learn.dto.AddressDto;
import com.fadili.learn.models.Address;
import com.fadili.learn.models.UserApp;
import com.fadili.learn.repositories.AddressRepository;
import com.fadili.learn.repositories.UserRepository;
import com.fadili.learn.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<AddressDto> getAddressesByUser(String userID) {

		UserApp userApp = userRepository.findByUserId(userID);

		List<Address> addresses = addressRepository.findAllByUserApp(userApp);

		List<AddressDto> addressDtos = new ArrayList<AddressDto>();
		addresses.forEach(address -> {
			AddressDto addressDto = new AddressDto();
			BeanUtils.copyProperties(address, addressDto);
			addressDtos.add(addressDto);
		});

		return addressDtos;
	}

	@Override
	public AddressDto getAddressesByIdAndByUser(String userID, String addressID) {
		
		UserApp userApp = userRepository.findByUserId(userID);
		Address address = addressRepository.findByUserAppAndAddressId(userApp, addressID);
		
		AddressDto addressDto = new AddressDto();
		BeanUtils.copyProperties(address, addressDto);
		
		return addressDto;
	}

}
