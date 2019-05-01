package com.hrboot.hrsupport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrboot.hrsupport.converter.DTOSkillConverter;
import com.hrboot.hrsupport.dto.SkillDTO;
import com.hrboot.hrsupport.model.Skill;
import com.hrboot.hrsupport.repository.SkillRepository;

@Service
public class SkillService
{
	@Autowired
	SkillRepository repo;
	
	@Autowired
	DTOSkillConverter conv;
	
	
	public SkillDTO findById(Long id)
	{
		Optional<Skill> skill = repo.findById(id);
		
		if(skill.isPresent())
			return conv.convertToDTO(skill.get());
		else
			return null;
	}
	
	public List<SkillDTO> findAll()
	{
		Optional<List<Skill>> list = Optional.of(repo.findAll());
		
		List<SkillDTO> listDto = new ArrayList<SkillDTO>();
		
		if(list.isPresent())
		{
			for(Skill skill : list.get())
				listDto.add(conv.convertToDTO(skill));
			
			return listDto;
		}
		else
			return null;
	}
	
	public boolean saveOne(SkillDTO dto)
	{
		
		Optional<Skill> skill = repo.findById(dto.getId());	
		
		if(skill.isPresent()) //check if already exists
			return false;
		else
		{
			repo.save(conv.convertFromDTO(dto));
			return true;
		}
	}
	
	
	public SkillDTO updateOne(Long id, SkillDTO dto)
	{
		Optional<Skill> skill = repo.findById(id);
		
		if(skill.isPresent())
		{
//			skill.get().setId(conv.convertFromDTO(dto).getId());
			skill.get().setSkill(conv.convertFromDTO(dto).getSkill());
			skill.get().setDescription(conv.convertFromDTO(dto).getDescription());
			
			repo.save(skill.get());
			
			return conv.convertToDTO(skill.get());
		}
		else
			return null;
	}
	
	public boolean deleteOne(Long id)
	{
		Optional<Skill> delSkill = repo.findById(id);
		
		if(delSkill.isPresent())
		{
			repo.deleteById(id);
			return true; //deleted
		}
		else
			return false;
	}
}
