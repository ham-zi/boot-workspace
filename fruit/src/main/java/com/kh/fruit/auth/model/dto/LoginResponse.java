package com.kh.fruit.auth.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginResponse {
	private String userId;
	private String userPwd;
	private String email;
	private String role;
	private String AccessToken;
	private String refreshToken;
}
