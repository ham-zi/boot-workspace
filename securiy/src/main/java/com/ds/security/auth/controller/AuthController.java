package com.ds.security.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.security.auth.model.dto.LoginDto;
import com.ds.security.auth.model.service.AuthService;
import com.ds.security.auth.model.vo.LoginResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody@Valid LoginDto login) {
		
		LoginResponse loginResponse = authService.login(login);
		
		return ResponseEntity.ok().body(loginResponse);
	}
	
}
