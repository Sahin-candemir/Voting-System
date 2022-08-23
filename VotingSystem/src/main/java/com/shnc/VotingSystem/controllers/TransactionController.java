package com.shnc.VotingSystem.controllers;


import java.util.List;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shnc.VotingSystem.dto.OptionDto;
import com.shnc.VotingSystem.dto.VoteAndOptionsResponse;
import com.shnc.VotingSystem.dto.VoteDto;
import com.shnc.VotingSystem.dto.VotingSaveRequest;
import com.shnc.VotingSystem.entities.Option;
import com.shnc.VotingSystem.entities.Vote;
import com.shnc.VotingSystem.services.OptionService;
import com.shnc.VotingSystem.services.VoteService;

@RestController
@RequestMapping("/transaction")
public class TransactionController<R> {
	
	private OptionService optionService;
	
	private VoteService voteService;
	
	private ModelMapper modelMapper;
	
	public TransactionController(OptionService optionService,VoteService voteService,ModelMapper modelMapper) {
		this.optionService = optionService;
		this.voteService = voteService;
		this.modelMapper = modelMapper;
	}

	@PostMapping("/{userId}/{voteId}/{optionId}")
	public ResponseEntity<String> addCount(@PathVariable(name = "userId") Long userId,@PathVariable(name = "voteId") Long voteId ,@PathVariable(name = "optionId") Long optionId){
		optionService.addCount(userId,voteId,optionId);
		return new ResponseEntity<>("Voting successfully!",HttpStatus.OK);
	}
	/*@GetMapping("/{voteId}")
	public List<Option> getVotingResults(@PathVariable Long voteId){
		List<Option> options = optionService.getAllOptionsWithVoteId(voteId);
		return options;
	}
	*/
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	@Transactional
	public ResponseEntity<VotingSaveRequest> createVoting(@RequestBody VotingSaveRequest votingSaveRequest){
		
		List<Option> options = votingSaveRequest.getOption();
		
		VoteDto voteResponse = voteService.createVote(modelMapper.map(votingSaveRequest.getVote(), VoteDto.class));
		options.stream().map(option->{return optionService.createOption(voteResponse.getId(), option);}).collect(Collectors.toList());
		//Option optionResponse = optionService.createOption(voteResponse.getId(), votingSaveRequest.getOption());
		votingSaveRequest.setVote(modelMapper.map(voteResponse, Vote.class));
		//votingSaveRequest.setOption(optionResponse);
		return new ResponseEntity<>(votingSaveRequest,HttpStatus.OK);
	}
	@GetMapping("/voteAndOptions/{id}")                         
	@Transactional
	public VoteAndOptionsResponse getAllVoteAndOptionsByVoteId(@PathVariable Long id){
		Vote vote = modelMapper.map(voteService.getVoteById(id), Vote.class) ;
		//List<VoteAndOptionsResponse> response = new ArrayList<>();
		
			List<OptionDto> options = optionService.getAllOptionsWithVoteId(vote.getId());
			
			VoteAndOptionsResponse voteAndOptionsResponse = new VoteAndOptionsResponse();
			voteAndOptionsResponse.setVoteId(id);
			voteAndOptionsResponse.setDescription(vote.getDescription());
			voteAndOptionsResponse.setTitle(vote.getTitle());
			voteAndOptionsResponse.setOptions(options);
		return voteAndOptionsResponse;
	}
	
	}
