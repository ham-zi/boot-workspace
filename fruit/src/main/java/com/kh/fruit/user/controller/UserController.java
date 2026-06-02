package com.kh.fruit.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.fruit.user.model.dto.UserDto;
import com.kh.fruit.user.model.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
	
	private final UserService service;
	
	@PostMapping
	public ResponseEntity<String> singUp(@Valid @RequestBody UserDto user){
		service.signUp(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("성공");
	}
	
	
	
	
	
	
	
	
	
	
	@GetMapping
	public ResponseEntity<List<UserDto>> findAll() {
		List<UserDto>users = service.findAll();
		return ResponseEntity.ok(users);
	}
}
