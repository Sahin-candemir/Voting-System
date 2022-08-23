package com.shnc.VotingSystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shnc.VotingSystem.dto.VoteDto;
import com.shnc.VotingSystem.entities.Vote;
import com.shnc.VotingSystem.services.VoteService;

@RestController
@RequestMapping("/votes")
public class VoteController {

	@Autowired
	private VoteService voteService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<VoteDto> createVote(@Valid @RequestBody VoteDto voteDto){
		return  new ResponseEntity<>(voteService.createVote(voteDto),HttpStatus.CREATED);
	}
	@CrossOrigin
	@GetMapping
	public List<VoteDto> getAllVotes(){
		return voteService.getAllVotes();
	}
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<VoteDto> getVoteById(@PathVariable Long id){
		return new ResponseEntity<>(voteService.getVoteById(id),HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Vote> updateVote(@PathVariable Long id, @RequestBody Vote newVote){
		return new ResponseEntity<>(voteService.updateVote(id,newVote),HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVoteById(@PathVariable Long id){
		voteService.deleteVoteById(id);
		return new ResponseEntity<>("Vote deleted succesfully.",HttpStatus.OK);
	}
}
