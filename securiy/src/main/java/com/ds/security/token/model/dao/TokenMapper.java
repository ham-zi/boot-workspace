package com.ds.security.token.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.ds.security.token.model.dto.RefreshTokenDto;

@Mapper
public interface TokenMapper {
	@Insert("INSERT INTO DS_TOKEN VALUES(#{token}, #{username}, #{expiration})")
	void saveToken(RefreshTokenDto token);
}
