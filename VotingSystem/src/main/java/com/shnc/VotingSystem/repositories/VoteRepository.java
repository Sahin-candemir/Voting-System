package com.shnc.VotingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shnc.VotingSystem.entities.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{

}
