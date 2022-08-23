package com.shnc.VotingSystem.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteDto {

	private Long id;
	
	private String title;
	
	private String description;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date lastDate;
	
	private int maxAge;
	
	private int minAge;
	
	private String genderRestriction;
}
