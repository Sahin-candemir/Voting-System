package com.shnc.VotingSystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shnc.VotingSystem.dto.UserDto;
import com.shnc.VotingSystem.dto.UserResponse;
import com.shnc.VotingSystem.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	private UserService userService;
	
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}


	@PostMapping
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserDto userDto){
		return new ResponseEntity<>(userService.createUser(userDto),HttpStatus.CREATED);
	}
	@GetMapping
	public List<UserResponse> getAllUser(){
		return userService.getAllUser();
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
		return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,@RequestBody UserDto newUser){
		return new ResponseEntity<>(userService.updateUser(id,newUser),HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return new ResponseEntity<>("User deleted succesfully!",HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
