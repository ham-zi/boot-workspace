package com.ds.legacy.auth.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.legacy.auth.model.dto.LoginRequestDto;
import com.ds.legacy.auth.model.dto.LoginResponse;
import com.ds.legacy.auth.model.service.AuthService;
import com.ds.legacy.member.model.dto.MemberDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService service;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequestDto loginDto){
		LoginResponse rl = service.login(loginDto);
		
		
		
		
		
		
		return ResponseEntity.ok(rl);
	}
}
