package com.shnc.VotingSystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.shnc.VotingSystem.dto.VoteDto;
import com.shnc.VotingSystem.entities.Option;
import com.shnc.VotingSystem.entities.Vote;
import com.shnc.VotingSystem.exceptions.ResourceNotFoundException;
import com.shnc.VotingSystem.repositories.VoteRepository;

@Service
public class VoteServiceImpl implements VoteService{

	private VoteRepository voteRepository;
	
	private OptionService optionService;
	
	private ModelMapper modelMapper;

	public VoteServiceImpl(ModelMapper modelMapper,VoteRepository voteRepository, @Lazy OptionService optionService) {
		this.voteRepository = voteRepository;
		this.optionService = optionService;
		this.modelMapper = modelMapper;
	}

	@Override
	public VoteDto createVote(VoteDto voteDto) {
		return modelMapper.map(voteRepository.save(modelMapper.map(voteDto, Vote.class)), VoteDto.class) ;
	}

	@Override
	public List<VoteDto> getAllVotes() {
		List<Vote> votes = voteRepository.findAll();
		return votes.stream().map(vote->modelMapper.map(vote, VoteDto.class)).collect(Collectors.toList());
	}

	@Override
	public VoteDto getVoteById(Long id) {
		Vote vote = voteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Vote is Not Found with id"));
		return modelMapper.map(vote, VoteDto.class);
	}

	@Override
	public Vote updateVote(Long id,Vote newVote) {
	/*	Vote vote = voteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Vote is Not Found with id"));
		List<OptionDto> options = optionService.getAllOptionsWithVoteId(id);
		for(Option o : options) {
			if(o.getCount()>0) {
				throw new SecurityException("Unable to update because there are votes used.");
			}
		}
		vote.setTitle(newVote.getTitle());
		vote.setDescription(newVote.getDescription());
		vote.setGenderRestriction(newVote.getGenderRestriction());
		vote.setLastDate(newVote.getLastDate());
		vote.setMinAge(newVote.getMinAge());
		vote.setMaxAge(newVote.getMaxAge());
		voteRepository.save(vote);
		*/
		return null;
	}

	@Override
	public void deleteVoteById(Long id) {
		Vote vote = voteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Vote is Not Found with id"));
		voteRepository.delete(vote);
	}

}
