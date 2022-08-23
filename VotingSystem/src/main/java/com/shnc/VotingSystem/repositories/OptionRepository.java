package com.shnc.VotingSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shnc.VotingSystem.entities.Option;

public interface OptionRepository extends JpaRepository<Option, Long>{

	List<Option> findAllByVoteId(Long VoteId);
}
