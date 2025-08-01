package com.media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.media.dto.UserDto;
import com.media.repository.UserRepository;
import com.media.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@PostMapping("/users")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

		UserDto savedUser = userService.registerUser(userDto);
		return new ResponseEntity<UserDto>(savedUser, HttpStatus.ACCEPTED);
	}

	@GetMapping("/users/{userid}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userid") Integer id) throws Exception {
		UserDto userDto = userService.findUserById(id);

		// return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		return ResponseEntity.ok(userDto);

	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getUsers() {

		List<UserDto> usersDto = userService.getAllUsers();
		return ResponseEntity.ok(usersDto);

	}

	@GetMapping("/users/email/{email}")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) throws Exception {

		return ResponseEntity.ok(userService.findUserByEmail(email));
	}

	@PutMapping("/users/follow/{userId1}/{userId2}")
	public ResponseEntity<UserDto> followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2)
			throws Exception {

		UserDto userDto = userService.followUser(userId1, userId2);

		return ResponseEntity.ok(userDto);
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user, @PathVariable("userId") Integer id)
			throws Exception {

		UserDto updatedUser = userService.updateUser(user, id);

		return ResponseEntity.ok(updatedUser);

	}

	@GetMapping("/users/search")
	public ResponseEntity<List<UserDto>> searchUser(@RequestParam("query") String query) {

		List<UserDto> usersDto = userService.searchUser(query);

		return ResponseEntity.ok(usersDto);
	}

	@DeleteMapping("/users/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId) throws Exception {
		userService.deleteUser(userId);

		return ResponseEntity.ok("Employee Deleted");
	}

}
