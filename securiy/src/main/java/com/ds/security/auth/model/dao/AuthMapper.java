package com.ds.security.auth.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ds.security.member.model.dto.MemberDto;

@Mapper
public interface AuthMapper {
	
	MemberDto findByUserId(String username);

	
}
