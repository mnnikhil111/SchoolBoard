package com.school.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sba.util.responsedto.*;
import com.school.sba.entity.User;
import com.school.sba.util.ResponseStructure;
import com.school.sba.util.requestdto.UserRequest;

import jakarta.validation.Valid;



public interface UserService {
	
	public	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest);

	public ResponseEntity<ResponseStructure<UserResponse>> findById(int userId);
	public	ResponseEntity<ResponseStructure<List<UserResponse>>> findAll();
	public	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);
	
}
