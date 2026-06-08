package com.ds.security.token.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshTokenDto {
	private String token;
	private String username;
	private long expiration;
}
