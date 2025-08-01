package com.media.service;

import java.util.List;

import com.media.dto.UserDto;

public interface UserService {

	public UserDto registerUser(UserDto userDto);

	public UserDto findUserById(Integer userId) throws Exception;

	public List<UserDto> getAllUsers();

	public UserDto findUserByEmail(String email) throws Exception;

	public UserDto followUser(Integer userId1, Integer userId2) throws Exception;

	public UserDto updateUser(UserDto userDto, Integer userId) throws Exception;

	public List<UserDto> searchUser(String query);

	public String deleteUser(Integer userId) throws Exception;

}
