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

import com.hrboot.hrsupport.dto.SkillDTO;
import com.hrboot.hrsupport.service.SkillService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/skill")
public class SkillController
{
	@Autowired
	SkillService service;
	
	
	@GetMapping("/getone/{id}")
	@ApiOperation( value = "Finds one skill.", notes = "Returns one found skill.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "NOT_FOUND")})
	public ResponseEntity<SkillDTO> getSkill(@PathVariable Long id)
	{
		System.out.println("getSkill()");
		
		SkillDTO skillDto = service.findById(id);
		
		return (skillDto == null) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<SkillDTO>(skillDto, HttpStatus.OK);
	}
	
	
	@GetMapping("/getall")
	@ApiOperation( value = "Finds all skills.", notes = "Returns all skills.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "NOT_FOUND")})
	public ResponseEntity<List<SkillDTO>> getAllSkills()
	{
		System.out.println("getAllSkills()");
		
		List<SkillDTO> listDto = service.findAll();
		
		return (listDto == null) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<SkillDTO>>(listDto, HttpStatus.OK);
	}
	
	
	@PostMapping("/add/")
	@ApiOperation( value = "Creates one skill.", notes = "Returns created skill.", httpMethod="POST")
	@ApiResponses( value = { @ApiResponse( code = 201, message = "CREATED"),
							 @ApiResponse( code = 400, message = "BAD_REQUEST")})
	public ResponseEntity<SkillDTO> addSkill(@RequestBody SkillDTO dto)
	{
		System.out.println("addSkill()");
		
		return (!service.saveOne(dto)) ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) : new ResponseEntity<SkillDTO>(dto, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{id}")
	@ApiOperation( value = "Updates one selected skill.", notes = "Returns updated skill.", httpMethod="PUT")
	@ApiResponses( value = { @ApiResponse( code = 201, message = "CREATED"),
							 @ApiResponse( code = 400, message = "BAD_REQUEST")})
	public ResponseEntity<SkillDTO> updateSkill(@PathVariable("id") Long id, @RequestBody SkillDTO dto)
	{
		System.out.println("updateSkill()");
		
		SkillDTO updatedSkill = service.updateOne(id, dto);
		
		return (updatedSkill == null) ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) : new ResponseEntity<SkillDTO>(updatedSkill, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/delete/{id}")
	@ApiOperation( value = "Removes one skill.", notes = "Returns true if skill removed successfully.", httpMethod="DELETE")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 400, message = "BAD_REQUEST")})
	public ResponseEntity<Boolean> deleteSkill(@PathVariable("id") Long id)
	{
		System.out.println("deleteSkill()");
		
		return (!service.deleteOne(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}

