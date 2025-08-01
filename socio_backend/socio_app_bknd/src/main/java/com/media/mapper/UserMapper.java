package com.media.mapper;

import com.media.dto.UserDto;
import com.media.models.User;

public class UserMapper {

	public static UserDto mapToUserDto(User user) {
		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
				user.getGender(), user.getFollowers(), user.getFollowings(), user.getSavedPost());

	}

	public static User mapToUser(UserDto userDto) {
		return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(),
				userDto.getPassword(), userDto.getGender(), userDto.getFollowers(), userDto.getFollowings(),
				userDto.getSavedPost());

	}

}
