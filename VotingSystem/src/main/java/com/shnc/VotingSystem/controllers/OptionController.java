package com.shnc.VotingSystem.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shnc.VotingSystem.dto.OptionDto;
import com.shnc.VotingSystem.entities.Option;
import com.shnc.VotingSystem.services.OptionService;

@RestController
@RequestMapping("/options")
public class OptionController {

	@Autowired
	private OptionService optionService;
	
	@PostMapping("/{voteId}")
	public ResponseEntity<Option> createOption(@PathVariable Long voteId, @RequestBody Option option){
		return  new ResponseEntity<>(optionService.createOption(voteId,option),HttpStatus.CREATED);
	}
	@CrossOrigin
	@GetMapping("/voteOption/{voteId}")
	public List<OptionDto> getAllOptionsWithVoteId(@PathVariable Long voteId){
		return optionService.getAllOptionsWithVoteId(voteId);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Option> getOptionById(@PathVariable Long id){
		return new ResponseEntity<>(optionService.getOptionById(id),HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Option> updateOption(@PathVariable Long id, @RequestBody Option newOption){
		return new ResponseEntity<>(optionService.updateOption(id,newOption),HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public void deleteOptionById(@PathVariable Long id){
		optionService.deleteOptionById(id);
	}
	@PostMapping("/multipleOptionCreate/{voteId}")
	public void createMultipleOption(@PathVariable Long voteId,@RequestBody List<Option> options) throws IOException{
		
		options.stream().map(option->{return createOption(voteId, option);}).collect(Collectors.toList());
				
	}
}
