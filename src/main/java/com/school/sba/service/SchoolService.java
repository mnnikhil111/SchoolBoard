package com.school.sba.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.*;
import com.school.sba.util.ResponseStructure;




public interface SchoolService {

	



	ResponseEntity<ResponseStructure<School>> addSchool(School school);

//	ResponseEntity<ResponseStructure<School>> findSchool(int schoolId);
//
//	ResponseEntity<ResponseStructure<School>> updateSchool(int schoolId, School school);
//
//	ResponseEntity<ResponseStructure<School>> deleteSchool(int schoolId);



	
	
	
	
}
