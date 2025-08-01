package com.media.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media.dto.UserDto;
import com.media.mapper.UserMapper;
import com.media.models.User;
import com.media.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto registerUser(UserDto userDto) {

		UserDto newUser = new UserDto();
		// newUser.setId(userDto.getId());
		newUser.setFirstName(userDto.getFirstName());
		newUser.setLastName(userDto.getLastName());
		newUser.setEmail(userDto.getEmail());
		newUser.setPassword(userDto.getPassword());
		newUser.setGender(userDto.getGender());
		newUser.setFollowers(userDto.getFollowers());
		newUser.setFollowings(userDto.getFollowings());
		newUser.setSavedPost(userDto.getSavedPost());

		User savedUser = userRepository.save(UserMapper.mapToUser(userDto));

		return UserMapper.mapToUserDto(savedUser);
	}

	@Override
	public UserDto findUserById(Integer userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent())
			return UserMapper.mapToUserDto(user.get());

		throw new Exception("User not exists with userid " + userId);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> allUsers = userRepository.findAll();

		return allUsers.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
	}

	@Override
	public UserDto findUserByEmail(String email) throws Exception {

		Optional<User> user = userRepository.findByEmail(email);

		if (user.isEmpty())
			throw new Exception("User not exists with email " + email);

		return UserMapper.mapToUserDto(user.get());
	}

	@Override
	public UserDto followUser(Integer userId1, Integer userId2) throws Exception {

		UserDto user1 = findUserById(userId1);
		UserDto user2 = findUserById(userId2);

		user2.getFollowers().add(user1.getId());
		user1.getFollowings().add(user2.getId());

		userRepository.save(UserMapper.mapToUser(user1));
		userRepository.save(UserMapper.mapToUser(user2));

		return user1;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) throws Exception {

		Optional<User> user1 = userRepository.findById(id);

		if (user1.isEmpty())
			throw new Exception("User not exists with userid " + id);

		User oldUser = user1.get();

		if (userDto.getFirstName() != null) {
			oldUser.setFirstName(userDto.getFirstName());
		}

		if (userDto.getLastName() != null) {
			oldUser.setLastName(userDto.getLastName());
		}
		if (userDto.getEmail() != null) {
			oldUser.setEmail(userDto.getEmail());
		}
		if (userDto.getPassword() != null) {
			oldUser.setPassword(userDto.getPassword());
		}

		User updatedUser = userRepository.save(oldUser);
		return UserMapper.mapToUserDto(updatedUser);

	}

	@Override
	public List<UserDto> searchUser(String query) {

		List<User> users = userRepository.searchUser(query);
		return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

	}

	@Override
	public String deleteUser(Integer userId) throws Exception {
		Optional<User> user1 = userRepository.findById(userId);

		if (user1.isEmpty())
			throw new Exception("User not exists with userid " + userId);

		userRepository.deleteById(userId);

		return "user deleted successfully with id " + userId;
	}

}
