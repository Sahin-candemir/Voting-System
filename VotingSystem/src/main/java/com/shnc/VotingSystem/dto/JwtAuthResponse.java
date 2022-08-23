package com.shnc.VotingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse {

	private String accessToken;
	
	private String TokenType="Bearer ";

	public JwtAuthResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	
	
}
