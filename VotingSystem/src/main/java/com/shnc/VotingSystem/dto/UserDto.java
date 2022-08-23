package com.shnc.VotingSystem.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	@NotEmpty(message ="Username shouldn't be empty." )
	@NotNull(message = "Username shouldn't be null.")
	private String userName;
	
	private String password;
	
	@NotEmpty(message ="City shouldn't be empty." )
	@NotNull(message = "City shouldn't be null.")
	private String city;
	
	@NotEmpty(message ="Gender shouldn't be empty." )
	@NotNull(message = "Gender shouldn't be null.")
	private String gender;
	
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date birtDate;
}
