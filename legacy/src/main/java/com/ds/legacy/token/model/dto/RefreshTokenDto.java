package com.ds.legacy.token.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshTokenDto {
	private String userId;
	private String refreshToken;
	private Long expiration;
}
