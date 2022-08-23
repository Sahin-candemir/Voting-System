package com.shnc.VotingSystem.entities;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name="options")
public class Option {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String item;
	
	private int count=0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="vote_id",referencedColumnName = "id")
    @JsonBackReference
	private Vote vote;
}
