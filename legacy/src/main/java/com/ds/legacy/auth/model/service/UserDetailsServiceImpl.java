package com.ds.legacy.auth.model.service;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ds.legacy.auth.model.AuthMapper;
import com.ds.legacy.auth.model.vo.CustomUserDetails;
import com.ds.legacy.member.model.dto.MemberDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	private final AuthMapper authMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDto user = authMapper.loadUser(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("조회된 정보가 없습니다.");
		}
		
		return CustomUserDetails.builder()
				                .username(user.getUserId())
				                .password(user.getUserPwd())
				                .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())))
				                .status(user.getStatus())
				                .build();
	}
	
	
	
}
