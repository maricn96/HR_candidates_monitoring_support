package com.hrboot.hrsupport.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrboot.hrsupport.converter.DTOCandidateConverter;
import com.hrboot.hrsupport.converter.DTOSkillConverter;
import com.hrboot.hrsupport.dto.CandidateDTO;
import com.hrboot.hrsupport.dto.SkillDTO;
import com.hrboot.hrsupport.model.Candidate;
import com.hrboot.hrsupport.model.Skill;
import com.hrboot.hrsupport.repository.CandidateRepository;
import com.hrboot.hrsupport.repository.SkillRepository;

@Service
public class CandidateService
{
	@Autowired
	CandidateRepository candRepo;
	
	@Autowired
	SkillRepository skillRepo;
	
	@Autowired
	DTOCandidateConverter candConv;
	
	@Autowired
	DTOSkillConverter skillConv;
	
	
	public CandidateDTO findById(Long id)
	{
		Optional<Candidate> cand = candRepo.findById(id);
		
		if(cand.isPresent()) //check if object contain value
			return candConv.convertToDTO(cand.get());
		else
			return null;
	}
	
	public List<CandidateDTO> findAll()
	{
		Optional<List<Candidate>> list = Optional.of(candRepo.findAll());
		
		List<CandidateDTO> listDto = new ArrayList<CandidateDTO>();
		
		if(list.isPresent())
		{
			for(Candidate cand : list.get())
				listDto.add(candConv.convertToDTO(cand));
			
			return listDto;
		}
		else
			return null;
	}
	
	public CandidateDTO saveOne(CandidateDTO dto)
	{
		Optional<Candidate> cand = candRepo.findById(dto.getId());
						
		
		if(cand.isPresent())//if candidate already exists -> return
			return null;
		else
		{			
			candRepo.save(candConv.convertFromDTO(dto));
			return dto;
		}
	}
	
	public CandidateDTO updateOne(Long id, CandidateDTO dto)
	{
		Optional<Candidate> cand = candRepo.findById(id);
		
		if(cand.isPresent())
		{
			cand.get().setId(candConv.convertFromDTO(dto).getId());
			cand.get().setName(candConv.convertFromDTO(dto).getName());
			cand.get().setSurname(candConv.convertFromDTO(dto).getSurname());
			cand.get().setBirthDate(candConv.convertFromDTO(dto).getBirthDate());
			cand.get().setPhoneNumber(candConv.convertFromDTO(dto).getPhoneNumber());
			cand.get().setEmail(candConv.convertFromDTO(dto).getEmail());
			cand.get().setSkills(candConv.convertFromDTO(dto).getSkills());
			
			candRepo.save(cand.get());
			
			return candConv.convertToDTO(cand.get());
		}
		else
			return null;
	}
	
	public boolean deleteOne(Long id)
	{
		Optional<Candidate> delCand = candRepo.findById(id);
		
		if(delCand.isPresent())
		{
			candRepo.deleteById(id);
			return true; //deleted
		}
		else
			return false;
	}
	
	
	/*
	 * Returns skills that candidate not contain
	 */
	public List<SkillDTO> getSkills(Long id) 
	{
		Optional<Candidate> cand = candRepo.findById(id);
		List<Skill> allSkills = skillRepo.findAll();
		
		allSkills.removeAll(cand.get().getSkills());
			
		Candidate bean = new Candidate();
		bean.setSkills(allSkills);
		
		List<SkillDTO> retVal = candConv.convertToDTO(bean).getSkills();
		
		return retVal;
	}
	
	
	/*
	 * Takes string that contain user search input divided by commas..
	 * Returns all candidates that contain at least one of string parts (not specific candidate that includes all parts).
	 */
	public List<CandidateDTO> search(String name)
	{
		Optional<List<Candidate>> list = Optional.of(candRepo.findAll());
		
		List<CandidateDTO> returnList = new ArrayList<CandidateDTO>();
		
		String splitted[] = name.split(","); //split string by comma
		
		if(list.isPresent())
		{
			for(Candidate cand : list.get())
			{
				for(int i=0; i<splitted.length; i++)
				{
					//set to lowercase and trim, then compare
					if(cand.getName().toLowerCase().contains(splitted[i].trim().toLowerCase()) || cand.getSurname().toLowerCase().contains(splitted[i].trim().toLowerCase()))
					{
						returnList.add(candConv.convertToDTO(cand));
					}
					else
					{
						//if result is empty, try with search in skill list
						if(cand.getSkills() != null)
						{
							for(Skill sk : cand.getSkills())
							{
								if(sk.getSkill().toLowerCase().contains(splitted[i].trim().toLowerCase()))
								{
									if(returnList.contains(candConv.convertToDTO(cand))) //remove duplicates
										break;
									else
										returnList.add(candConv.convertToDTO(cand));
								}
							}
						}
						else
							return null;
					}
				}
				
			}
			
			return returnList;
		}
		else
			return null;
	}
	
	/*
	 * Function that add skills to candidate (update)
	 */
	public CandidateDTO manageCandidateSkill(CandidateDTO dto)
	{
		Optional<Candidate> cand = candRepo.findById(candConv.convertFromDTO(dto).getId());
		
		Set<Skill> finalSk = new HashSet<Skill>();

		finalSk.addAll(cand.get().getSkills());
		finalSk.addAll(candConv.convertFromDTO(dto).getSkills());

		cand.get().setSkills(candConv.convertFromDTO(dto).getSkills());
		candRepo.save(cand.get());

		return candConv.convertToDTO(cand.get());

	}	
	
	/*
	 * Returns candidate with deleted skill
	 */
	public CandidateDTO deleteCandidateSkill(CandidateDTO dto)
	{
		Optional<Candidate> cand = candRepo.findById(candConv.convertFromDTO(dto).getId());
		
		Candidate candToDel = candConv.convertFromDTO(dto);
		
		if (cand.isPresent()) 
		{
			
			Iterator<Skill> alSkIter = cand.get().getSkills().iterator();

			while (alSkIter.hasNext())
			{
				for(Skill skk : candToDel.getSkills())
				{
					if(!alSkIter.next().getId().equals(skk.getId()))
						break;
					else
						alSkIter.remove();
						
				}
			}

			candRepo.save(cand.get());

			return candConv.convertToDTO(cand.get());

		}
		return null;
	}

	
}
