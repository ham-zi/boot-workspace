package com.kh.semi.auth.model.service;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.kh.semi.auth.model.dto.LoginRequestDto;
import com.kh.semi.auth.model.vo.CustomUserDetails;
import com.kh.semi.exception.CustomAuthenticationException;
import com.kh.semi.token.model.service.TokenService;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	public void login(LoginRequestDto lrd) {
		
		//로그인(인증/Authentication) 구현
		
		/*1. 유효성 검증(아이디/ 비밀번호값이 들어왔는가, 영어숫자인가, 글자수가 괜찮은가) -> @Valid로 대체
		 *2. 아이디가 SEMI_MEMBER테이블에 MEMBER_ID컬럼에 존재하는 아이디인가? -> loadUserByUserName
		 *3. 조회를 해온 비밀번호 컬럼의 암호문이 사용자가 입력한 평문으로 만들어진 것이 맞는가? -> Provider가 해줌
		 *
		 *
		 *사람마다 작동코드가 다를 수 있다.->?
		 *
		 */
		Authentication auth =null;
		try {
			auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(lrd.getMemberId(),lrd.getMemberPwd()));
		} catch(AuthenticationException e) {
			throw new CustomAuthenticationException("아이디 또는 비밀번호가 틀렸습니다.");
		}
		
		//인증에 성공함
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		// log.info("로그인한 사용자의 정보: {}", user);
		// 토큰발급
		tokenService.getTokens(user);
		log.info("2");
		
		/*
		Jwts.builder().subject(user.getUsername())
		              .issuedAt(new Date())
		              .expiration(new Date())
		              .compact();
		 */
	}

	
}
