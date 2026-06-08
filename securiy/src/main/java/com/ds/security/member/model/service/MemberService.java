package com.ds.security.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ds.security.exception.NotFoundIdException;
import com.ds.security.member.model.dao.MemberMapper;
import com.ds.security.member.model.dto.MemberDto;
import com.ds.security.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
	private final MemberMapper mapper;
	private final PasswordEncoder passwordEncoder;
	public void save(MemberDto member) {
		availableMemberId(member.getUserId());
		
		Member user = Member.builder()
				            .userId(member.getUserId())
				            .userPwd(passwordEncoder.encode(member.getUserPwd()))
				            .userName(member.getUserName())
				            .email(member.getEmail())
				            .build();
		mapper.save(user);
	}
	
	private void availableMemberId(String memberId) {
		if(mapper.findByMemberId(memberId) != null) {
			throw new NotFoundIdException("아이디가 존재합니다.");
		}
	}
	
}
