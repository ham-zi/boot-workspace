package com.kh.fruit.token.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.fruit.auth.model.vo.CustomUserDetails;
import com.kh.fruit.exception.CustomAuthenticationException;
import com.kh.fruit.token.model.dao.TokenMapper;
import com.kh.fruit.token.model.vo.RefreshToken;
import com.kh.fruit.token.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

		private final JwtUtil tokenUtil;
		private final TokenMapper tokenMapper;
	
		public Map<String, String> getTokens(CustomUserDetails user) {
			Map<String,String> tokens = createTokens(user);
			saveToken(tokens.get("refreshToken"), user.getUsername());
			
			return tokens;
		}
		
		private Map<String,String> createTokens(CustomUserDetails user) {
			return Map.of("accessToken", tokenUtil.getAccessToken(user),
					      "refreshToken", tokenUtil.getRefreshToken(user));
		}
	
		private void saveToken(String token, String userId) {
			RefreshToken refreshToken = RefreshToken.builder()
					                                .userId(userId)
					                                .token(token)
					                                .expiration(System.currentTimeMillis() + (1000*60*60*24*5))
					                                .build();
			tokenMapper.saveToken(refreshToken);
		}
		
		public Map<String, String> tokenRotation(String refreshToken) {
			RefreshToken token = tokenMapper.findByToken(refreshToken);
			if(token == null || token.getExpiration() < System.currentTimeMillis()) {
				throw new CustomAuthenticationException("유효하지 않은 토큰입니다.");
			}
		}
		
}
