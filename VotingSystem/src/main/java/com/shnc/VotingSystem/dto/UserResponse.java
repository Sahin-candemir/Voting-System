package com.shnc.VotingSystem.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

	private String userName;
	
	private String city;
	
	private String gender;
	
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date birtDate;
}
