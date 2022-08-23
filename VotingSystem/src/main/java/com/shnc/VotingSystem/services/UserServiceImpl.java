package com.shnc.VotingSystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shnc.VotingSystem.dto.UserDto;
import com.shnc.VotingSystem.dto.UserResponse;
import com.shnc.VotingSystem.entities.User;
import com.shnc.VotingSystem.exceptions.ResourceNotFoundException;
import com.shnc.VotingSystem.exceptions.UsernameAllreadyExists;
import com.shnc.VotingSystem.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;

	private ModelMapper modelMapper;
	
	 PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserResponse createUser(UserDto userDto) {
		if(userRepository.existsByUserName(userDto.getUserName())) {
			throw new UsernameAllreadyExists();
		}
		User user = modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userRepository.save(user);
		return modelMapper.map(user, UserResponse.class);
	}

	@Override
	public List<UserResponse> getAllUser() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user->modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
	}

	@Override
	public UserResponse getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not Found with id"));
		return modelMapper.map(user, UserResponse.class);
	}

	@Override
	public UserResponse updateUser(Long id, UserDto newUser) {
		User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not Found with id"));
		user.setBirtDate(newUser.getBirtDate());
		user.setCity(newUser.getCity());
		user.setGender(newUser.getGender());
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		user.setUserName(newUser.getUserName());
		userRepository.save(user);
		return modelMapper.map(user, UserResponse.class);
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not Found with id"));
		userRepository.delete(user);
	}

}
