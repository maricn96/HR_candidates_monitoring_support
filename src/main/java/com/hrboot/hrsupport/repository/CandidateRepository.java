package com.hrboot.hrsupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrboot.hrsupport.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>
{
	
}
