package com.ds.legacy.token.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;


import com.ds.legacy.auth.model.vo.CustomUserDetails;
import com.ds.legacy.token.model.TokenMapper;
import com.ds.legacy.token.model.dto.RefreshTokenDto;
import com.ds.legacy.token.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

	private final JwtUtil tokenUtil;
	private final TokenMapper tokenMapper;
	

	
	public Map<String, String> getTokens(CustomUserDetails user) {
		String refreshToken = tokenUtil.getRefreshToken(user);
		saveToken(refreshToken, user);
		return Map.of("accessToken", tokenUtil.getAccessToken(user), "refreshToken", refreshToken);
	}
	
	private void saveToken(String refreshToken, CustomUserDetails user) {
		tokenMapper.saveToken(RefreshTokenDto.builder()
				                             .userId(user.getUsername())
				                             .refreshToken(refreshToken)
				                             .expiration(System.currentTimeMillis()+(1000*60*60*24*3))
				                             .build());
	}
}
