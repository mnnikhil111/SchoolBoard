package com.school.sba.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.util.ResponseStructure;

import com.school.sba.Service.School_Service;
import com.school.sba.entity.School;



@RequestMapping("/schools")
@RestController
public class SchoolController {
	
	@Autowired
	private School_Service school_service;
	
//	//Response Structure
	@PostMapping
	public ResponseEntity<ResponseStructure<School>> addStudent(@RequestBody School school) {
		return school_service.addSchool(school);
	}
	
//	@GetMapping
//	public ResponseEntity<ResponseStructure<School>> findStudent(@RequestParam int schoolId) {
//		return school_service.findSchool(schoolId);
//	}
//	
//	//update the student table 
//	@PutMapping
//	public ResponseEntity<ResponseStructure<School>> updateStudent(@RequestParam int schoolId,@RequestBody School school) {
//		return school_service.updateSchool(schoolId, school);
//	}
//	
//	
//	//Delete the record by Id
//	@DeleteMapping
//	public ResponseEntity<ResponseStructure<School>> deleteStudent(@RequestParam int schoolId) {
//		return school_service.deleteSchool(schoolId);
//	}
//	
	

}
