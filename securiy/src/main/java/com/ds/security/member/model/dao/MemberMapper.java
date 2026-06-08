package com.ds.security.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ds.security.member.model.dto.MemberDto;
import com.ds.security.member.model.vo.Member;

@Mapper
public interface MemberMapper {
	void save(Member member);
	MemberDto findByMemberId(String memberId);
}
