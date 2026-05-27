package com.kh.study.member.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.kh.study.member.model.dto.MemberDto;

@Mapper
public interface MemberMapper {
	@Insert("INSERT INTO MEMBER VALUES (#{userId}, #{userPwd}, #{userName}, #{email}, SYSDATE, SYSDATE, 'Y')")
	int enroll(MemberDto member);
}
