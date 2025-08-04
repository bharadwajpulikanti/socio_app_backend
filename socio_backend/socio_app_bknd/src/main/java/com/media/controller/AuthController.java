package com.media.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.media.config.JwtProvider;
import com.media.models.User;
import com.media.repository.UserRepository;
import com.media.request.LoginRequest;
import com.media.response.AuthResponse;
import com.media.service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passowordEncoder;

	@Autowired
	private CustomUserDetailsService customUserDetails;

	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {

		Optional<User> isExist = userRepository.findByEmail(user.getEmail());

		if (isExist.isPresent())
			throw new Exception("User already exists with email " + user.getEmail());

		User newUser = new User();
		// newUser.setId(user.getId());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(passowordEncoder.encode(user.getPassword()));
		newUser.setGender(user.getGender());
		newUser.setFollowers(user.getFollowers());
		newUser.setFollowings(user.getFollowings());
		newUser.setSavedPost(user.getSavedPost());

		User savedUser = userRepository.save(newUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());

		String token = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse(token, "Registration Success");

		return res;
	}

	@PostMapping("/signin")
	public AuthResponse signIn(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		String token = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse(token, "Login Success");

		return res;
	}

	private Authentication authenticate(String email, String password) {

		UserDetails userDetails = customUserDetails.loadUserByUsername(email);

		if (userDetails == null) {
			throw new BadCredentialsException("Invalid Username");
		}

		if (!passowordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
