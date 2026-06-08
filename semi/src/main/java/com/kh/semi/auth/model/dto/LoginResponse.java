package com.kh.semi.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoginResponse {
	
	private String memberId;
	private String memberName;
	private String role;
	private String AccessToken;
	private String refreshToken;

}
