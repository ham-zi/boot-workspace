package com.kh.semi.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.semi.auth.model.dto.LoginRequestDto;
import com.kh.semi.auth.model.service.AuthService;
import com.kh.semi.member.model.dto.MemberDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
		private final AuthService authService;
		
		@PostMapping("/login")
		public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto lrd) {
			authService.login(lrd);
			log.info("1");
			return null;
		}
}
