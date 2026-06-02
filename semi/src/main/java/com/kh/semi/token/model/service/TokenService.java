package com.kh.semi.token.model.service;

import org.springframework.stereotype.Service;

import com.kh.semi.auth.model.vo.CustomUserDetails;
import com.kh.semi.token.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

	private final JwtUtil tokenUtil;
	
	public void getTokens(CustomUserDetails user) {
		String accessToken = tokenUtil.getAccessToken(user);
	}
}
