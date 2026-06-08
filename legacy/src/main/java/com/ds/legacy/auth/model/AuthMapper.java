package com.ds.legacy.auth.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ds.legacy.member.model.dto.MemberDto;

@Mapper
public interface AuthMapper {
	@Select("SELECT USER_NO, USER_PWD, USER_ID, USER_NAME, EMAIL, ROLE, ENROLL_DATE, MODIFY_DATE, STATUS FROM DS_MEMBER WHERE USER_ID = #{userId} AND STATUS = 'Y'")
	public MemberDto loadUser(String username);
}
