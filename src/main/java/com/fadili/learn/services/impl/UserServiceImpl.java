package com.fadili.learn.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fadili.learn.dto.token.ConfirmationToken;
import com.fadili.learn.dto.token.ConfirmationTokenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fadili.learn.dto.UserAppDto;
import com.fadili.learn.exceptions.UserAlreadyExistException;
import com.fadili.learn.exceptions.UserAppNotFoundException;
import com.fadili.learn.models.Address;
import com.fadili.learn.models.UserApp;
import com.fadili.learn.repositories.UserRepository;
import com.fadili.learn.services.UserService;
import com.fadili.learn.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private Utils utils;

	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserAppDto createUser(UserAppDto userDto) {

		UserApp userByEmail = userRepository.findByEmail(userDto.getEmail());

		if (userByEmail != null)
			throw new UserAlreadyExistException("this email already Exists !!");

		UserApp user = new UserApp();
		// BeanUtils.copyProperties(userDto, user);
		ModelMapper modelMapper = new ModelMapper();
		user = modelMapper.map(userDto, UserApp.class);

		List<Address> addresses = userDto.getAddresses().stream()
				.map(address -> modelMapper.map(address, Address.class)).collect(Collectors.toList());
		
		
		user.setAddresses(addresses);

		user.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setUserId(utils.generateRandomKey(30));
		for (Address address : user.getAddresses()) {
			address.setAddressId(utils.generateRandomKey(29));
			address.setUserApp(user);
		}

		UserApp savedUser = userRepository.save(user);

		// BeanUtils.copyProperties(savedUser, userDto);
		userDto = modelMapper.map(savedUser, UserAppDto.class);

		String token = UUID.randomUUID().toString();

		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
								LocalDateTime.now().minusMinutes(10), null, savedUser);

		confirmationTokenService.saveConfirmationToken(confirmationToken);

		return userDto;
	}

	@Override
	public UserAppDto getUserByUserId(String userID) {
		UserApp userApp = this.userRepository.findByUserId(userID);

		if (userApp == null)
			throw new UserAppNotFoundException(userID);

		UserAppDto userAppDto = new UserAppDto();

		// BeanUtils.copyProperties(userApp, userAppDto);
		ModelMapper modelMapper = new ModelMapper();
		userAppDto = modelMapper.map(userApp, UserAppDto.class);

		return userAppDto;
	}

	@Override
	public UserAppDto updateUser(String userID, UserAppDto userDto) {

		UserApp userApp = this.userRepository.findByUserId(userID);

		if (userApp == null)
			throw new UserAppNotFoundException(userID);

		userApp.setFirstName(userDto.getFirstName());
		userApp.setLastName(userDto.getLastName());

		UserApp savedUser = this.userRepository.save(userApp);

		UserAppDto userAppDto = new UserAppDto();

		// BeanUtils.copyProperties(savedUser, userAppDto);
		ModelMapper modelMapper = new ModelMapper();
		userAppDto = modelMapper.map(savedUser, UserAppDto.class);

		return userAppDto;
	}

	@Override
	public void deleteUser(String userID) {

		UserApp userApp = this.userRepository.findByUserId(userID);

		if (userApp == null)
			throw new UserAppNotFoundException(userID);

		this.userRepository.delete(userApp);
	}

	@Override
	public List<UserAppDto> getUsers(int page, int limit) {

		List<UserAppDto> userAppDtoList = new ArrayList<UserAppDto>();

		Pageable pageable = PageRequest.of(page - 1, limit);

		Page<UserApp> userPage = this.userRepository.findAll(pageable);
		List<UserApp> users = userPage.getContent();
		ModelMapper modelMapper = new ModelMapper();
		for (UserApp userApp : users) {
			UserAppDto userAppDto = new UserAppDto();
			// BeanUtils.copyProperties(userApp, userAppDto);
			userAppDto = modelMapper.map(userApp, UserAppDto.class);
			userAppDtoList.add(userAppDto);
		}

		return userAppDtoList;
	}

}
