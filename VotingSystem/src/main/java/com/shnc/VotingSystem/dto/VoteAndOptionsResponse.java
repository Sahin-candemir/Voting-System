package com.shnc.VotingSystem.dto;

import java.util.List;

import com.shnc.VotingSystem.entities.Option;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteAndOptionsResponse {

	private Long voteId;
	
	private String description;
	
	private String title;
	
	private List<OptionDto> options;
}
