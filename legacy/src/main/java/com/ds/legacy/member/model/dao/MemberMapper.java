package com.ds.legacy.member.model.dao;

import java.util.List;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ds.legacy.member.model.dto.MemberDto;
import com.ds.legacy.member.model.vo.Member;

@Mapper
public interface MemberMapper {
	
	@Select("SELECT USER_NO, USER_ID, USER_NAME, EMAIL, ENROLL_DATE, ROLE,  MODIFY_DATE, STATUS FROM DS_MEMBER WHERE USER_ID = #{userId} AND STATUS = 'Y'")
	public MemberDto findById(String userId);
	
	@Select("SELECT * FROM DS_MEMBER WHERE STATUS = 'Y' ORDER BY USER_NO DESC")
	public List<MemberDto> findAll();
	
	@Select("SELECT * FROM DS_MEMBER WHERE STATUS = 'Y' AND USER_NO = #{userNo}")
	public MemberDto findByNo(Long userNo);
	
	@Insert("INSERT INTO DS_MEMBER(USER_NO ,USER_ID, USER_PWD, USER_NAME, EMAIL) VALUES(SEQ_DS_MNO.NEXTVAL, #{userId}, #{userPwd}, #{userName}, #{email})")
	public int save(Member memberVo); 
}
