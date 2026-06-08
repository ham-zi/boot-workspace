package com.ds.security.auth.model.vo;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomUserDetails implements UserDetails{
	private String username;
	private Long userNo;
	private String password;
	private String userName;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
}
