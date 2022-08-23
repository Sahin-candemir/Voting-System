package com.shnc.VotingSystem.dto;

import java.util.List;

import com.shnc.VotingSystem.entities.Option;
import com.shnc.VotingSystem.entities.Vote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotingSaveRequest {

	private Vote vote;
	
	private List<Option> option;
}
