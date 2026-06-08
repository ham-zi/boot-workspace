package com.kh.fruit.auth.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomUserDetails implements UserDetails {
	private String username; // member_id 담겠음
	private String password;
	private String memberName;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	private String status;
}
