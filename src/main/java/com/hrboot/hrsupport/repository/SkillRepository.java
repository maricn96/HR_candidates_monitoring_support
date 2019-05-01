package com.hrboot.hrsupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrboot.hrsupport.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

}
