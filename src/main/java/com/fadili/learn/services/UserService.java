package com.fadili.learn.services;

import java.util.List;

import com.fadili.learn.dto.UserAppDto;

public interface UserService {
	
	UserAppDto createUser(UserAppDto userDto);
	List<UserAppDto> getUsers(int page, int limit);
	UserAppDto updateUser(String userID, UserAppDto userDto);
	void deleteUser(String userID);
	UserAppDto getUserByUserId(String userID);
	
}
