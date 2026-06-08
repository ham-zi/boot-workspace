package com.ds.legacy.token.util;

import java.time.Duration;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ds.legacy.auth.model.vo.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
	//토큰을 만드는 기능
	
	
	//토큰을 검증하는 기능
	
	
	// header, payload, signature
	// 엄청긴 문자열이 하나 필요함 D9uLKVc4FE0IEheN3pLLEb8GKhHRsG8cdOxazHnEkRm3CGAeB+xVbt8Dbob1QhRB
	@Value("${jwt.secret}")
	private String secretKey;
	private SecretKey key;
	

	
	@PostConstruct
	public void init() {
		//log.info("{}", secretKey);
		byte[] arr = Base64.getDecoder().decode(secretKey);
		this.key = Keys.hmacShaKeyFor(arr);
	}
	


	public String getAccessToken(CustomUserDetails user) {
		log.info("{}",secretKey);
		return Jwts.builder()
				   .subject(user.getUsername())
		           .issuedAt(new Date())
		           .expiration(Date.from(Instant.now().plus(Duration.ofMinutes(15))))
		           .signWith(key)
		           .compact();
		//.expiration(new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMinutes(15)))
        //.expiration(new Date(System.currentTimeMillis()+(1000*60*15))).compact();
		// 다른 토큰을 만들경우 3일 일주일 만드는데, 가독성이 더 좋음
	}
	
	public String getRefreshToken(CustomUserDetails user) {
		return Jwts.builder()
				   .subject(user.getUsername())
				   .issuedAt(new Date())
				   .expiration(Date.from(Instant.now().plus(Duration.ofDays(3))))
				   .signWith(key)
				   .compact();
	}
	
	public Claims parseJwt(String token) {
		return Jwts.parser()
				   .verifyWith(key)
				   .build()
				   .parseSignedClaims(token)
				   .getPayload();
	}
	
}