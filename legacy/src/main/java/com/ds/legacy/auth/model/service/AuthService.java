package com.ds.legacy.auth.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;


import com.ds.legacy.auth.model.AuthMapper;
import com.ds.legacy.auth.model.dto.LoginRequestDto;
import com.ds.legacy.auth.model.dto.LoginResponse;
import com.ds.legacy.auth.model.vo.CustomUserDetails;
import com.ds.legacy.exception.member.NotFoundIdException;
import com.ds.legacy.member.model.dto.MemberDto;
import com.ds.legacy.token.model.service.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
		
	private final AuthMapper mapper;
	private final AuthenticationManager manager;
	private final TokenService tokenService;
	
	public LoginResponse login(LoginRequestDto loginDto) {
		//아이디검증
		
		// 1. 사용자의 입력한 값을 담는 DTO를 만든다. / 사용자의 정보를 담는 DTO를 만든다.
		// 2. authenticationManager 객체를 빈등록한다
		// 3. userDetails (interface)의 구현체를 만들고
		// 4. userDetails에 username을 전달하면 mapper에서 id로 유저정보를 셀렉한다
		// 5. 반환할때 pro...에서 , passwordEncoding빈을 이용하여 비밀번호 .matches(평문,암호문)을 검사 해준다.
		// 6. authentication에 header, 토큰정보{ pk, 토큰발생시간, 토큰유효시간 } , 시그니처를 담는다.
		
		
		//토큰발급
		Authentication auth = null;
		try {
			auth = manager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserId(),loginDto.getUserPwd()));
		} catch (AuthenticationException e) {
			throw new NotFoundIdException("아이디 또는 비밀번호가 틀렸습니다.");
		}
		
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		Map<String,String> tokens = tokenService.getTokens(user);
		
		return LoginResponse.builder()
			                .userId(user.getUsername())
			                .role(user.getAuthorities().toString())
			                .accessToken(tokens.get("accessToken"))
			                .refreshToken(tokens.get("refreshToken"))
			                .build();
	}

}
