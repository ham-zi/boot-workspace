package com.kh.fruit.user.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.fruit.user.model.dto.UserDto;
import com.kh.fruit.user.model.vo.User;

@Mapper
public interface UserMapper {

	@Insert("INSERT INTO FRUIT_USER VALUES(SEQ_FRUIT_UNO.NEXTVAL, #{userId}, #{userPwd}, #{userName}, 'ROLE_USER', SYSDATE, 'Y')")
	public int signUp(User user);

	@Select("SELECT COUNT(*) FROM FRUIT_USER WHERE USER_ID = #{userId}")
	public int countUserId(String userId);

	@Select("SELECT * FROM FRUIT_USER ORDER BY USER_NO DESC")
	public List<UserDto> findAll();
}
