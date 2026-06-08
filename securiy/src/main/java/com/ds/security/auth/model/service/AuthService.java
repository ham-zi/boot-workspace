package com.ds.security.auth.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ds.security.auth.model.dao.AuthMapper;
import com.ds.security.auth.model.dto.LoginDto;
import com.ds.security.auth.model.vo.CustomUserDetails;
import com.ds.security.auth.model.vo.LoginResponse;
import com.ds.security.exception.NotFoundIdException;
import com.ds.security.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
	private final AuthMapper authMapper;
	private final AuthenticationManager manager;
	private final TokenService tokenService;
	
	public LoginResponse login(LoginDto login) {

		Authentication auth = null;
		try {
			auth = manager.authenticate(new UsernamePasswordAuthenticationToken(login.getUserId(), login.getUserPwd()));
		} catch(AuthenticationException e) {
			throw new NotFoundIdException("존재하지 않는 아이디입니다.");
		}
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();

 		Map<String,String> tokens = tokenService.getTokens(user);
		
		LoginResponse loginResponse = LoginResponse.builder()
				            .username(user.getUsername())
				            .role(user.getAuthorities().toString())
				            .accessToken(tokens.get("accessToken"))
				            .refreshToken(tokens.get("refreshToken"))
				            .build();
		return loginResponse;
	}
}
