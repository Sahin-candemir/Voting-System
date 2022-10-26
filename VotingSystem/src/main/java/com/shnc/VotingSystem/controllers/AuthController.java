package com.shnc.VotingSystem.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shnc.VotingSystem.dto.JwtAuthResponse;
import com.shnc.VotingSystem.dto.LoginDto;
import com.shnc.VotingSystem.dto.SignUpDto;
import com.shnc.VotingSystem.entities.Role;
import com.shnc.VotingSystem.entities.User;
import com.shnc.VotingSystem.repositories.RoleRepository;
import com.shnc.VotingSystem.repositories.UserRepository;
import com.shnc.VotingSystem.security.JwtTokenProvider;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {
//git asdfasdasdasd
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@ApiOperation(value = "REST API to Register or Signup user to Blog app")
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// get token form tokenProvider
		String token = tokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JwtAuthResponse(token));
	}

	@ApiOperation(value = "REST API to Signin or Login user to Blog app")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {

		// add check for username exists in a DB
		if (userRepository.existsByUserName(signUpDto.getUserName())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		// create user object
		User user = new User();
		user.setUserName(signUpDto.getUserName());
		user.setBirtDate(signUpDto.getBirtDate());
		user.setCity(signUpDto.getCity());
		user.setGender(signUpDto.getGender());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

		Role roles = roleRepository.findByName("ROLE_ADMIN").get();
		user.setRoles(Collections.singleton(roles));

		userRepository.save(user);

		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

	}
}
