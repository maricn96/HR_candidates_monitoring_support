package com.hrboot.hrsupport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrboot.hrsupport.dto.CandidateDTO;
import com.hrboot.hrsupport.dto.SkillDTO;
import com.hrboot.hrsupport.service.CandidateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/candidate")
@Api(tags = "")
public class CandidateController
{
	@Autowired
	CandidateService candService;
	
	@GetMapping("/getone/{id}")
	@ApiOperation( value = "Finds one job candidate.", notes = "Returns one found job candidate.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "NOT_FOUND")})
	public ResponseEntity<CandidateDTO> getCandidate(@PathVariable Long id)
	{
		System.out.println("getCandidate()");
		
		CandidateDTO candDto = candService.findById(id);
		
		return (candDto == null) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<CandidateDTO>(candDto, HttpStatus.OK);
	}
	
	
	@GetMapping("/getall")
	@ApiOperation( value = "Finds all job candidates.", notes = "Returns all found job candidate.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "NOT_FOUND")})
	public ResponseEntity<List<CandidateDTO>> getAllCandidates()
	{
		System.out.println("getAllCandidates()");
		
		List<CandidateDTO> listDto = candService.findAll();
		
		return (listDto == null) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<CandidateDTO>>(listDto, HttpStatus.OK);
	}
	
	
	@PostMapping("/add/")
	@ApiOperation( value = "Adds a new job candidate.", notes = "Returns created job candidate.", httpMethod="POST")
	@ApiResponses( value = { @ApiResponse( code = 201, message = "CREATED"),
							 @ApiResponse( code = 400, message = "BAD_REQUEST")})
	public ResponseEntity<CandidateDTO> addCandidate(@RequestBody CandidateDTO dto)
	{
		System.out.println("addCandidate()");
		
		return (candService.saveOne(dto) == null) ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) : new ResponseEntity<CandidateDTO>(dto, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{id}")
	@ApiOperation( value = "Updates one job candidate with set of skills.", notes = "Returns updated job candidate.", httpMethod="PUT")
	@ApiResponses( value = { @ApiResponse( code = 201, message = "CREATED"),
							 @ApiResponse( code = 400, message = "BAD_REQUEST")})
	public ResponseEntity<CandidateDTO> updateCandidate(@PathVariable("id") Long id, @RequestBody CandidateDTO dto)
	{
		System.out.println("updateCandidate()");
		
		CandidateDTO updatedCand = candService.updateOne(id, dto);
		
		return (updatedCand == null) ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) : new ResponseEntity<CandidateDTO>(updatedCand, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/delete/{id}")
	@ApiOperation( value = "Removes one job candidate.", notes = "Returns true if candidate removed succesfully.", httpMethod="DELETE")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 400, message = "BAD_REQUEST")})
	public ResponseEntity<Boolean> deleteCandidate(@PathVariable("id") Long id)
	{
		System.out.println("deleteCandidate()");
		
		return (!candService.deleteOne(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST) : new ResponseEntity<Boolean>(true, HttpStatus.OK); 
	}
	
	
	@GetMapping("/getskills/{id}")
	@ApiOperation( value = "Searches for all skills that candidate doesn't contain.", notes = "Returns list of skills that candidate doesn't contain.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "NOT_FOUND")})
	public ResponseEntity<List<SkillDTO>> getRemainSkills(@PathVariable("id") Long id)
	{
		System.out.println("getCandidateSkills()");
		
		List<SkillDTO> returnList = candService.getSkills(id);
		
		return (returnList == null || returnList.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<SkillDTO>>(returnList, HttpStatus.OK);		
	}
	
	
	@GetMapping("/search/{name}")
	@ApiOperation( value = "Searches for candidates that match one or more parameters.", notes = "Returns list of candidates that match to parameters.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "NOT_FOUND")})
	public ResponseEntity<List<CandidateDTO>> searchCandidates(@PathVariable("name") String name)
	{
		System.out.println("search()");
		
		List<CandidateDTO> list = candService.search(name);
		
		return (list == null) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<CandidateDTO>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/addcandidateskill")
	@ApiOperation( value = "Adds one or more skills to selected candidate.", notes = "Returns candidate with updated skills.", httpMethod="PUT")
	@ApiResponses( value = { @ApiResponse( code = 201, message = "CREATED"),
							 @ApiResponse( code = 400, message = "BAD_REQUEST")})
	public ResponseEntity<CandidateDTO> addCandidateSkills(@RequestBody CandidateDTO skills)
	{
		System.out.println("addCandidateSkill()");
		
		CandidateDTO dto = candService.manageCandidateSkill(skills);
		
		return (dto == null) ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) : new ResponseEntity<CandidateDTO>(dto, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/deletecandidateskill")
	@ApiOperation( value = "Delete one selected skill from candidate.", notes = "Returns candidate with updated skills.", httpMethod="DELETE")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 400, message = "BAD_REQUEST")})
	public ResponseEntity<CandidateDTO> deleteSkill(@RequestBody CandidateDTO dto)
	{
		System.out.println("deletecandidateskill()");
		
		CandidateDTO updatedCand = candService.deleteCandidateSkill(dto);
		
		return (updatedCand == null) ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) : new ResponseEntity<CandidateDTO>(updatedCand, HttpStatus.OK);
	}
	
	
}

