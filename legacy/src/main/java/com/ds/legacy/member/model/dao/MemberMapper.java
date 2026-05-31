package com.ds.legacy.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ds.legacy.member.model.dto.MemberDto;

@Mapper
public interface MemberMapper {
	@Select("SELECT * FROM DS_MEMBER WHERE STATUS = 'Y' ORDER BY USER_NO DESC")
	public List<MemberDto> findAll();
	
	@Select("SELECT * FROM DS_MEMBER WHERE STATUS = 'Y' AND USER_NO = #{userNo}")
	public MemberDto findByNo(Long userNo); 
}
