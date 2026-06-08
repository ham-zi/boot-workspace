package com.ds.legacy.token.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.ds.legacy.token.model.dto.RefreshTokenDto;

@Mapper
public interface TokenMapper {
	@Insert("INSERT INTO DS_TOKEN VALUES (#{refreshToken}, #{userId}, #{expiration})")
	void saveToken(RefreshTokenDto tokenDto);
}
