package com.shnc.VotingSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameAllreadyExists extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "Username already exist.";

	public UsernameAllreadyExists() {
		super(MESSAGE);
	}

}
