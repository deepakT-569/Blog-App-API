package com.deepak.blog.services;

import com.deepak.blog.dto.UserDto;
import com.deepak.blog.entities.User;
import com.deepak.blog.response.UserResponse;

public interface IUserService {

	User createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	UserResponse getAllUser(Integer pageNumber ,Integer pageSize);
	
	void deleteUser(Integer userId);
	
}
