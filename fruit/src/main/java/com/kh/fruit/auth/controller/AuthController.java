package com.kh.fruit.auth.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.fruit.auth.model.dto.LoginRequest;
import com.kh.fruit.auth.model.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("login")
	public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest lr) {
		
		authService.login(lr);

		
		return ResponseEntity.ok().build();
	}

}
