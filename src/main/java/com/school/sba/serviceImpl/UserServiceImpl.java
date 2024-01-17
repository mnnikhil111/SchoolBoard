package com.school.sba.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.school.sba.repository.*;
import com.school.sba.entity.User;
import com.school.sba.enums.UserRole;
import com.school.sba.exception.ConstraintViolationException;
import com.school.sba.exception.UserNotFoundByIdException;
import com.school.sba.service.UserService;
import com.school.sba.util.ResponseStructure;
import com.school.sba.util.requestdto.UserRequest;
import com.school.sba.util.responsedto.UserResponse;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	int count;
	
	@Autowired
	private ResponseStructure<UserResponse> responseStructure;
	
	
	private User mapToUser(UserRequest request)
	{
		return User.builder()
				.userName(request.getUserName())
				.password(request.getUserPassword())
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.contactNo(request.getContactNo())
				.userRole(request.getUserRole())
				.email(request.getUserEmail())
				
				
				.build();
	}
	
	
	private UserResponse mapToUserResponse(User user) {
		
		return UserResponse.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.contactNo(user.getContactNo())
				.userRole(user.getUserRole())
				.email(user.getEmail())
				
				.build();
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {
		
		User user1=mapToUser(userRequest);
		if(userRequest.getUserRole()==UserRole.ADMIN)
		{
			count+=1;
		}
		System.out.println("count value is "+count);
		if(count==1||userRequest.getUserRole()!=UserRole.ADMIN)
		{
			try
			{
				 user1=userRepository.save(mapToUser(userRequest));
				 
			}
			catch (Exception e) {
				throw new ConstraintViolationException("Duplicate Entry made",HttpStatus.IM_USED,"No Duplicate entries are allowed");
				
			}
		}
		else
		{
			throw new ConstraintViolationException("There is already an admin",HttpStatus.IM_USED,"More than one admin is not allowed");
			
		}
		
		
		
		
		
		responseStructure.setStatus(HttpStatus.CREATED.value()); //manual status 
		responseStructure.setMessage("user added succesfully");
		responseStructure.setData(mapToUserResponse(user1));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);
	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findById(int userId){
		
		User user1=userRepository.findById(userId).map(u->{
			
			return u;
		}).orElseThrow(()->new UserNotFoundByIdException("user not found by id"));
			
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("user found succesfully");
		responseStructure.setData(mapToUserResponse(user1));
		
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.FOUND);
		
	}


	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAll(){
		
		ResponseStructure<List<UserResponse>> listResponseStructure =new ResponseStructure<>();
		
		List<User> users=userRepository.findAll();
		
		List <UserResponse> userResponse=users.stream()
				.map(this::mapToUserResponse)
				.collect(Collectors.toList());
		listResponseStructure.setStatus(HttpStatus.FOUND.value());
		listResponseStructure.setMessage("user found ");
		listResponseStructure.setData(userResponse);
		
		
		return new ResponseEntity<ResponseStructure<List<UserResponse>>>(listResponseStructure,HttpStatus.FOUND);
		
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId){
		
		User user1=userRepository.findById(userId).map(u->{
			
			userRepository.delete(u);
			return u;
			
		}
		
				).orElseThrow(()-> new RuntimeException());
		
		userRepository.delete(user1);
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("user deleted succesfully");
		responseStructure.setData(mapToUserResponse(user1));
		
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);
	}
	

	
	



}
