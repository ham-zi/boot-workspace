package com.kh.study.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.study.member.model.dao.MemberMapper;
import com.kh.study.member.model.dto.MemberDto;

@Service
public class MemberService {
	@Autowired
	private MemberMapper mapper;
	
	public int enroll(MemberDto member) {
		return mapper.enroll(member);
	}
}
