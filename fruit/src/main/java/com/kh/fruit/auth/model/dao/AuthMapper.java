package com.kh.fruit.auth.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.fruit.user.model.dto.UserDto;

@Mapper
public interface AuthMapper {
	@Select("SELECT USER_NO, USER_ID, USER_PWD, USER_NAME, ROLE, CREATE_DATE, STATUS FROM FRUIT_USER WHERE USER_ID = #{userId} AND STATUS = 'Y'")
	UserDto findById(String userId);
}
