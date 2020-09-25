package com.wow.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wow.server.data.model.User;
import com.wow.server.data.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping (value= {"/api/users"}, method=RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers () {
		
		return ResponseEntity.ok(userRepository.findAll());
		
	}
	
}
