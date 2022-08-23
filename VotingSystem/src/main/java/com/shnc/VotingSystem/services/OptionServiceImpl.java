package com.shnc.VotingSystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.shnc.VotingSystem.dto.OptionDto;
import com.shnc.VotingSystem.entities.Option;
import com.shnc.VotingSystem.entities.UserVote;
import com.shnc.VotingSystem.entities.Vote;
import com.shnc.VotingSystem.exceptions.ResourceNotFoundException;
import com.shnc.VotingSystem.exceptions.UserAndVoteAllreadyExists;
import com.shnc.VotingSystem.repositories.OptionRepository;
import com.shnc.VotingSystem.repositories.UserVoteRepository;

@Service
public class OptionServiceImpl implements OptionService{

	private UserVoteRepository userVoteRepository;
	
	private OptionRepository optionRepository;
	
	private VoteService voteService;
	
	private ModelMapper modelMapper;
	
	public OptionServiceImpl(OptionRepository optionRepository, VoteService voteService,ModelMapper modelMapper,UserVoteRepository userVoteRepository) {
		this.optionRepository = optionRepository;
		this.voteService = voteService;
		this.modelMapper = modelMapper;
		this.userVoteRepository = userVoteRepository;
	}

	@Override
	public Option createOption(Long voteId, Option option) {
		Vote vote =modelMapper.map(voteService.getVoteById(voteId), Vote.class) ;
		option.setVote(vote);
		return optionRepository.save(option);
	}

	@Override
	public Option getOptionById(Long id) {
		Option option = optionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Option not found with id"));
		return option;
	}

	@Override
	public Option updateOption(Long id, Option newOption) {
		Option option = optionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Option not found with id"));
		option.setItem(newOption.getItem());
		return optionRepository.save(option);
	}

	@Override
	public List<OptionDto> getAllOptionsWithVoteId(Long voteId) {
		List<Option> options = optionRepository.findAllByVoteId(voteId);
		return options.stream().map(option->modelMapper.map(option, OptionDto.class)).collect(Collectors.toList());
	}

	@Override
	public void deleteOptionById(Long id) {
		optionRepository.deleteById(id);	
	}
	public void addCount(Long userId,Long voteId,Long optionId) {
		Option op = optionRepository.findById(optionId).orElseThrow(()-> new ResourceNotFoundException("Option not found with id"));
		if(op.getVote().getId()!=voteId) {
			throw new ResourceNotFoundException("Option not found with voteId");
		}
		
				if(userVoteRepository.existsByUserIdAndVoteId(userId, voteId)) {
					throw new UserAndVoteAllreadyExists("User has allready voted.");
				}
				UserVote userVote = new UserVote();
				userVote.setUserId(userId);
				userVote.setVoteId(voteId);
				userVoteRepository.save(userVote);
				Option option = optionRepository.findById(optionId).orElseThrow(()-> new ResourceNotFoundException("Option not found with id"));
				option.setCount(option.getCount()+1);
				optionRepository.save(option);

			}
		

		
		
	
	

}
