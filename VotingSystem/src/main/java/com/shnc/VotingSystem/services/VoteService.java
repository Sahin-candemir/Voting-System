package com.shnc.VotingSystem.services;

import java.util.List;

import com.shnc.VotingSystem.dto.VoteDto;
import com.shnc.VotingSystem.entities.Vote;

public interface VoteService {

	VoteDto createVote(VoteDto voteDto);

	List<VoteDto> getAllVotes();

	VoteDto getVoteById(Long id);

	Vote updateVote(Long id, Vote newVote);

	void deleteVoteById(Long id);

}
