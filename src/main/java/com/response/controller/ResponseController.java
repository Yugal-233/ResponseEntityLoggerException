package com.response.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.response.exception.UserNotFoundException;
import com.response.exception.ValidationException;
import com.response.model.User;
import com.response.service.ResponseService;

@RestController
@RequestMapping("/response")
public class ResponseController {

	@Autowired
	private ResponseService responseService;

	private final Logger logger = Logger.getLogger(RestController.class.getName());

	@PostMapping("/user")
	public ResponseEntity<User> saveUserData(@RequestBody User user) {
		try {
			User userData = responseService.saveUserDetails(user);
			logger.log(Level.INFO, "user saved successfully", userData);
			return new ResponseEntity<>(userData, HttpStatus.CREATED);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error saving the data:" + ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			List<User> users = responseService.getAllUserDetails();
			if (users.isEmpty()) {
				return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (ValidationException ex) {
			return ResponseEntity.ok(new ArrayList<>());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error fetching all users: " + ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findUserById(User user, @PathVariable int id) {
		try {
			User userById = responseService.findUserById(user, id);
			if (userById == null) {
				throw new UserNotFoundException("user not found: " + id);
			}
			return ResponseEntity.ok(userById);
		} catch (UserNotFoundException ex) {
			logger.log(Level.WARNING, "User not found: " + ex.getMessage());
			throw ex;
		}
	}

}
