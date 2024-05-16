package com.response.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.response.model.User;
import com.response.repo.ResponseRepo;

@Service
public class ResponseService {
	
	@Autowired
	private ResponseRepo responseRepo;

	public User saveUserDetails(User user) {
		return responseRepo.save(user);
	}
	
	public List<User> getAllUserDetails(){
		return responseRepo.findAll();
	}
	public User findUserById(User user, int id) {
		return responseRepo.findById(id).orElse(null);
	}
}
