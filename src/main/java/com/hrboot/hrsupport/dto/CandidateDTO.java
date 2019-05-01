package com.hrboot.hrsupport.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO 
{
	private Long id;
	private String name;
	private String surname;
	private Date birthDate;
	private String phoneNumber;
	private String email;
	
	private List<SkillDTO> skills;
}
