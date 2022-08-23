package com.shnc.VotingSystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shnc.VotingSystem.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String userName);
	
	Boolean existsByUserName(String userName);
}
