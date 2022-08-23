package com.shnc.VotingSystem.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SignUpDto {

	private String userName;
	
	private String password;
	
	private String city;
	
	private String gender;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date birtDate;
}
