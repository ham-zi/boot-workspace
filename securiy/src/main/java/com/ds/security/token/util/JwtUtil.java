package com.ds.security.token.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ds.security.auth.model.vo.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secretKey;
	private SecretKey key;
	
	@PostConstruct
	public void init() {
		byte[] arr = Base64.getDecoder().decode(secretKey);
		this.key = Keys.hmacShaKeyFor(arr);
	}
	
	
	public String getAccessToken(CustomUserDetails user) {
		String accessToken = Jwts.builder()
		    .subject(user.getUsername())
		    .issuedAt(new Date())
		    .expiration(Date.from(Instant.now().plus(Duration.ofHours(24))))
		    .signWith(key)
		    .compact();
		return accessToken;
	}
	
	public String getRefreshToken(CustomUserDetails user) {
		String refreshToken = Jwts.builder()
			    .subject(user.getUsername())
			    .issuedAt(new Date())
			    .expiration(Date.from(Instant.now().plus(Duration.ofHours(72))))
			    .signWith(key)
			    .compact();
			return refreshToken;
	}
	
	public Claims parseJwt(String token) {
		return Jwts.parser()
				   .verifyWith(key)
				   .build()
				   .parseSignedClaims(token)
				   .getPayload();
	}
	
}
