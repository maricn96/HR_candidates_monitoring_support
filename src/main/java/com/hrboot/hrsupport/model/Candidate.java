package com.hrboot.hrsupport.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 32)
	private String name;
	
	@NotNull
	@Size(min = 2, max = 32)
	private String surname;
	
	private Date birthDate;
	
	@NotNull
	private String phoneNumber;
	
	@NotNull
	
	private String email;
	
	@ManyToMany
	@JoinTable(name = "candidate_skills",
			joinColumns = @JoinColumn(name = "candidate_id"),
			inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private List<Skill> skills;
	
}
