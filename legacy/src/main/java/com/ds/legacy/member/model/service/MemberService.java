package com.ds.legacy.member.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ds.legacy.member.model.dao.MemberMapper;
import com.ds.legacy.member.model.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberMapper mapper;
	
	public List<MemberDto> findAll(){
		return mapper.findAll();
	}
	
	public MemberDto findByNo(Long userNo) {
		return mapper.findByNo(userNo);
	}

	public MemberDto login(MemberDto member) {
		//검증
		
		//
		
		return null;
	}
}
