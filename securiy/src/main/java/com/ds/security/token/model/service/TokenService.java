package com.ds.security.token.model.service;



import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ds.security.auth.model.vo.CustomUserDetails;
import com.ds.security.token.model.dao.TokenMapper;
import com.ds.security.token.model.dto.RefreshTokenDto;
import com.ds.security.token.util.JwtUtil;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class TokenService {
	private final JwtUtil jwtUtil;
	private final TokenMapper mapper;
	
	@Transactional
	public Map<String,String> getTokens(CustomUserDetails user){
		String refreshToken = jwtUtil.getRefreshToken(user);
		String accessToken = jwtUtil.getAccessToken(user);
		saveToken(refreshToken, user.getUsername());
		Map<String, String> map = Map.of("accessToken", accessToken, "refreshToken", refreshToken);
		return map;
	}
	
	public void saveToken(String token, String username) {
		RefreshTokenDto tokenDto = RefreshTokenDto.builder().token(token).username(username).expiration(System.currentTimeMillis()+(1000*60*60*24*3)).build();
		mapper.saveToken(tokenDto);

	}
	
}
