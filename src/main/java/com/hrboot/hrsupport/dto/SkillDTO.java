package com.hrboot.hrsupport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO
{
	private Long id;
	private String skill;
	private String description;
	
	//private List<CandidateDTO> candidates;
}
