package com.hrboot.hrsupport.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrboot.hrsupport.dto.CandidateDTO;
import com.hrboot.hrsupport.dto.SkillDTO;
import com.hrboot.hrsupport.model.Candidate;
import com.hrboot.hrsupport.model.Skill;

@Component
public class DTOCandidateConverter
{
	@Autowired
	DTOSkillConverter skillConv;
	
	public CandidateDTO convertToDTO(Candidate model)
	{
		CandidateDTO dto = new CandidateDTO();
		
		dto.setId(model.getId());
		dto.setName(model.getName());
		dto.setSurname(model.getSurname());
		dto.setBirthDate(model.getBirthDate());
		dto.setPhoneNumber(model.getPhoneNumber());
		dto.setEmail(model.getEmail());
		
		List<SkillDTO> listDto = new ArrayList<SkillDTO>();
		
		for(Skill skill : model.getSkills())
		{
			listDto.add(skillConv.convertToDTO(skill));
		}
		
		dto.setSkills(listDto);
		
		return dto;
	}
	
	public Candidate convertFromDTO(CandidateDTO dto)
	{
		Candidate bean = new Candidate();
		
		bean.setId(dto.getId());
		bean.setName(dto.getName());
		bean.setSurname(dto.getSurname());
		bean.setBirthDate(dto.getBirthDate());
		bean.setPhoneNumber(dto.getPhoneNumber());
		bean.setEmail(dto.getEmail());
		
		List<Skill> listBean = new ArrayList<Skill>();
		
		for(SkillDTO skill : dto.getSkills())
		{
			listBean.add(skillConv.convertFromDTO(skill));
		}
		
		bean.setSkills(listBean);
		return bean;
		
	}
}
