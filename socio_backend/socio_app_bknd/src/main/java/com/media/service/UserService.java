package com.media.service;

import java.util.List;

import com.media.exceptions.UserException;
import com.media.models.User;

public interface UserService {

	public User registerUser(User user);

	public User findUserById(Integer userId) throws UserException;

	public List<User> getAllUsers();

	public User findUserByEmail(String email) throws UserException;

	public User followUser(Integer userId1, Integer userId2) throws UserException;

	public User updateUser(User user, Integer userId) throws UserException;

	public List<User> searchUser(String query);

	public String deleteUser(Integer userId) throws UserException;

	public User findUserByJwt(String jwt);

}
