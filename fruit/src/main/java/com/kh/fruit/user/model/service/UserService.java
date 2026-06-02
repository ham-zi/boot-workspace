package com.kh.fruit.user.model.service;


import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.fruit.exception.DuplicateUserIdException;
import com.kh.fruit.exception.FailSignUpException;
import com.kh.fruit.user.model.dao.UserMapper;
import com.kh.fruit.user.model.dto.UserDto;
import com.kh.fruit.user.model.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper mapper;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public void signUp(UserDto user) {
		// 1. 아이디 중복 검사
		availableUserId(user.getUserId());
		// 2. 비밀번호 암호화
		User userInfo = userDtoEncoding(user);
		// 3. transaction
		
		// 4. 유효성 검증 
		int result = mapper.signUp(userInfo);
		if(result != 1) {
			throw new FailSignUpException("회원가입 실패...관리자에게 문의하세요");
		}
	}
	
	private void availableUserId(String userId) {
		if (mapper.countUserId(userId) > 0) {
			throw new DuplicateUserIdException("아이디가 중복입니다.");
		}
	}
	
	private User userDtoEncoding(UserDto user) {
		availableUserId(user.getUserId());
		User userInfo = User.builder().userId(user.getUserId())
				                      .userPwd(passwordEncoder.encode(user.getUserPwd()))
				                      .userName(user.getUserName())
				                      .build();	
		return userInfo;
	}
	
	
	
	
	
	
	
	
	public List<UserDto> findAll() {
		return mapper.findAll();
		
	}
	
	
}
