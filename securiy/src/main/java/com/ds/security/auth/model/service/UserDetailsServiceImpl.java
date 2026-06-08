package com.ds.security.auth.model.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ds.security.auth.model.dao.AuthMapper;
import com.ds.security.auth.model.vo.CustomUserDetails;
import com.ds.security.member.model.dto.MemberDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService{

	private final AuthMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDto member = mapper.findByUserId(username);
		if(member == null) {
			throw new UsernameNotFoundException("아이디가 없습니다.");
		}
		
		return CustomUserDetails.builder()
				                .username(member.getUserId())
				                .password(member.getUserPwd())
				                .userNo(member.getUserNo())
				                .userName(member.getUserName())
				                .email(member.getEmail())
				                .authorities(Collections.singletonList(new SimpleGrantedAuthority(member.getRole())))
				                .build();
	}

}
