package com.shnc.VotingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shnc.VotingSystem.entities.UserVote;

public interface UserVoteRepository extends JpaRepository<UserVote,Long >{

	Boolean existsByUserIdAndVoteId(Long userId,Long voteId);
}
