package com.school.sba.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.School;
import com.school.sba.exception.SchoolNotFoundByIdException;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.service.SchoolService;
import com.school.sba.util.ResponseStructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class SchoolServiceImpl implements SchoolService {
	
	
	@Autowired
	private SchoolRepository schoolrepository;

	@Override
	public ResponseEntity<ResponseStructure<School>> addSchool(School school) {
		
		School school2 =  schoolrepository.save(school);
		ResponseStructure<School> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("School Object Created Successfully");
		responseStructure.setData(school2);
		
		return new ResponseEntity<ResponseStructure<School>>(responseStructure,HttpStatus.CREATED);
		
	}

//	@Override
//	public ResponseEntity<ResponseStructure<School>> findSchool(int schoolId) {
//		Optional<School> optional=schoolrepository.findById(schoolId);
//		if(optional.isPresent()) {
//			School school=optional.get();
//			ResponseStructure<School> responseStructure=new ResponseStructure<>();
//			responseStructure.setStatus(HttpStatus.FOUND.value());
//			responseStructure.setMessage("School Object found Successfully");
//			responseStructure.setData(school);
//			
//			return new ResponseEntity<ResponseStructure<School>>(responseStructure,HttpStatus.FOUND);
//		}
//		else
//		{
//			throw new SchoolNotFoundByIdException("School Not Found");
//		}
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<School>> updateSchool(int schoolId, School updatedSchool) {
//		Optional<School> optional=schoolrepository.findById(schoolId);
//		
//		if(optional.isPresent()) {
//			School existingSchool=optional.get();
//			updatedSchool.setSchoolId(existingSchool.getSchoolId());
//			School school =schoolrepository.save(updatedSchool);
//			
//			ResponseStructure<School> responseStructure=new ResponseStructure<>();
//			responseStructure.setStatus(HttpStatus.OK.value());
//			responseStructure.setMessage("Student Object updated Successfully");
//			responseStructure.setData(school);
//			
//			return new ResponseEntity<ResponseStructure<School>>(responseStructure,HttpStatus.OK);
//		}
//		else
//		{
//			return null;
//		}
//		
//		
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<School>> deleteSchool(int schoolId) {
//		Optional<School> optional = schoolrepository.findById(schoolId);
//		if(optional.isPresent()) {
//			School school=optional.get();
//			schoolrepository.delete(school);
//			
//			ResponseStructure<School> responseStructure=new ResponseStructure<>();
//			responseStructure.setStatus(HttpStatus.OK.value());
//			responseStructure.setMessage("School Object deleted Successfully");
//			responseStructure.setData(school);
//			
//			return new ResponseEntity<ResponseStructure<School>>(responseStructure,HttpStatus.OK);
//		}
//		else
//		{
//			return null;
//		}
//	}
	
	


	




}
