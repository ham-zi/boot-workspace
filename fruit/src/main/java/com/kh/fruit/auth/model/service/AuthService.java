package com.kh.fruit.auth.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.kh.fruit.auth.model.dao.AuthMapper;
import com.kh.fruit.auth.model.dto.LoginRequest;
import com.kh.fruit.auth.model.dto.LoginResponse;
import com.kh.fruit.auth.model.vo.CustomUserDetails;
import com.kh.fruit.exception.CustomAuthenticationException;
import com.kh.fruit.exception.NotFoundUserIdException;
import com.kh.fruit.token.model.service.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

	private final AuthenticationManager manager;
	private final TokenService tokenService;
	
	public void login(@Valid LoginRequest lr) {

		Authentication auth = null;
		
		try {
		auth = manager.authenticate(new UsernamePasswordAuthenticationToken(lr.getUserId(),lr.getUserPwd()));
		} catch(AuthenticationException e) {
			throw new CustomAuthenticationException("아이디 또는 비밀번호가 틀렸습니다.");
		}
		
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		
		Map<String, String> tokens = tokenService.getTokens(user);
		
		return LoginResponse.builder()
		             .userId(user.getUsername())
		             .role(user.getAuthorities().toString())
		             .AccessToken(tokens.get("accessToken"))
		             .refreshToken(tokens.get("refreshToken"))
		             .build();
		
	}
	
}
