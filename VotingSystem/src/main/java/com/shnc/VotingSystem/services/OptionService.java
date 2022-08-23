package com.shnc.VotingSystem.services;

import java.util.List;

import com.shnc.VotingSystem.dto.OptionDto;
import com.shnc.VotingSystem.entities.Option;

public interface OptionService {

	Option createOption(Long voteId, Option option);

	Option getOptionById(Long id);

	Option updateOption(Long id, Option newOption);

	List<OptionDto> getAllOptionsWithVoteId(Long voteId);

	void deleteOptionById(Long id);

    void addCount(Long userId,Long voteId,Long optionId);

}
