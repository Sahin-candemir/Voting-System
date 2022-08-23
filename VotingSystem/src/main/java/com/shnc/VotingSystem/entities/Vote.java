package com.shnc.VotingSystem.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name="votes")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date lastDate;
	
	private int maxAge;
	
	private int minAge;
	
	private String genderRestriction;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vote", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Option> options = new ArrayList<>();
}
