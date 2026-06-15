package com.kh.semi.token.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.semi.auth.model.vo.CustomUserDetails;
import com.kh.semi.exception.CustomAuthenticationException;
import com.kh.semi.token.model.dao.TokenMapper;
import com.kh.semi.token.model.vo.RefreshToken;
import com.kh.semi.token.util.JwtUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

	private final JwtUtil tokenUtil;
	private final TokenMapper tokenMapper;
	
	public Map<String,String> getTokens(CustomUserDetails user) {
		//String accessToken = tokenUtil.getAccessToken(user);
		//String refreshToken = tokenUtil.getRefreshToken(user);
		//리프래쉬토큰은 DB에 저장
		Map<String,String> tokens = createTokens(user);
		saveToken(tokens.get("refreshToken"), user.getUsername());
		
		return tokens;
	}
	
	// 토큰을 만들어서 반환해주는 메소드
	private Map<String,String> createTokens(CustomUserDetails user) {
		return Map.of("accessToken", tokenUtil.getAccessToken(user),
				      "refreshToken", tokenUtil.getRefreshToken(user));
	}
	
	// 리프레시토큰을 받아서 DB에 인서트해주는 메소드
	private void saveToken(String token, String memberId) {
		
		RefreshToken refreshToken = RefreshToken.builder()
				                                .memberId(memberId)
				                                .token(token)
				                                .expiration(System.currentTimeMillis() + (1000*60*60*24*5))
				                                .build();
		tokenMapper.saveToken(refreshToken);
	}
	
	// 로그아웃 요청 왔을 때 DB에 있는 데이터 지워주는 메서드
	public void logout(String memberId) {
		tokenMapper.deleteToken(memberId);
	}
	// 추후 AccessToken의 만료기간이 지나서 토큰 갱신 요청이 들어왓을 때
	// 사용자에게 전달받은 RefreshToken이 DB에 존재하면서 만료기간이 지나지 않았는지 검증하는 메소드
	
	public Map<String, String> tokenRotation(String refreshToken) {
		RefreshToken token = tokenMapper.findByToken(refreshToken);
		if(token == null || token.getExpiration() < System.currentTimeMillis()) {
				throw new CustomAuthenticationException("유효하지 않은 토큰입니다.");
		}
		Claims claims = tokenUtil.parseJwt(token.getToken());
		String memberId = claims.getSubject();
		String memberName = (String)claims.get("memberName");
		CustomUserDetails user = CustomUserDetails.builder().memberName(memberName).username(memberId).build();
		Map<String,String> tokens = createTokens(user);
		saveToken(tokens.get("refreshToken"),memberId);
		return tokens;
	}
	
	
}
