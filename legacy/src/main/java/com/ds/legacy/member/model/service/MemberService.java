package com.ds.legacy.member.model.service;

import java.util.List;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ds.legacy.exception.member.DuplicationIdException;
import com.ds.legacy.exception.member.FailMemberSaveException;
import com.ds.legacy.exception.member.NotFoundIdException;
import com.ds.legacy.member.model.dao.MemberMapper;
import com.ds.legacy.member.model.dto.MemberDto;
import com.ds.legacy.member.model.vo.Member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberMapper mapper;
	private final PasswordEncoder passwordEncoder;
	
	public List<MemberDto> findAll(){
		return mapper.findAll();
	}
	
	public MemberDto findByNo(Long userNo) {
		return mapper.findByNo(userNo);
	}

	@Transactional
	public void save(MemberDto memberDto) {
		//아이디가 존재하는것인가?
		MemberDto member = mapper.findById(memberDto.getUserId());
		if(member != null) {
			throw new DuplicationIdException("아이디가 존재합니다.");
		}
		// 비밀번호 암호화
		Member memberVo = Member.builder()
		      .userId(memberDto.getUserId())
		      .userPwd(passwordEncoder.encode(memberDto.getUserPwd()))
		      .userName(memberDto.getUserName())
		      .email(memberDto.getEmail())
		      .build();
		// 트랜잭션처리
		
		// 검증	
		int result = mapper.save(memberVo);
		if(result != 1) {
			throw new FailMemberSaveException("회원가입 실패, 관리자에게 문의하세요.");
		}
		
	}
}
