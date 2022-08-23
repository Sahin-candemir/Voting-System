package com.shnc.VotingSystem.services;

import java.util.List;

import com.shnc.VotingSystem.dto.UserDto;
import com.shnc.VotingSystem.dto.UserResponse;

public interface UserService {

	UserResponse createUser(UserDto userDto);

	List<UserResponse> getAllUser();

	UserResponse getUserById(Long id);

	UserResponse updateUser(Long id, UserDto newUser);

	void deleteUser(Long id);

}
