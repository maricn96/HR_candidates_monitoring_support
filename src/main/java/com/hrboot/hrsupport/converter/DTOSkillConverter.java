package com.hrboot.hrsupport.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrboot.hrsupport.dto.SkillDTO;
import com.hrboot.hrsupport.model.Skill;

@Component
public class DTOSkillConverter
{
	@Autowired
	DTOCandidateConverter candConv;
	
	public SkillDTO convertToDTO(Skill model)
	{
		SkillDTO dto = new SkillDTO();
		
		dto.setId(model.getId());
		dto.setSkill(model.getSkill());
		dto.setDescription(model.getDescription());
		
		return dto;
	}
	
	public Skill convertFromDTO(SkillDTO dto)
	{
		Skill bean = new Skill();
		
		bean.setId(dto.getId());
		bean.setSkill(dto.getSkill());
		bean.setDescription(dto.getDescription());
		
		return bean;
		
	}
}
