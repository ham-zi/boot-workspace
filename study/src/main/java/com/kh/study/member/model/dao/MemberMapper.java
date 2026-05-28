package com.kh.study.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.study.member.model.dto.MemberDto;

@Mapper
public interface MemberMapper {
	@Insert("INSERT INTO MEMBER VALUES (#{userId}, #{userPwd}, #{userName}, #{email}, SYSDATE, SYSDATE, 'Y')")
	int enroll(MemberDto member);
	
	@Select("SELECT * FROM MEMBER")
	List<MemberDto>findAll();
	
	@Select("SELECT * FROM MEMBER WHERE USER_ID = #{userId}")
	MemberDto findById(String userId);
}
