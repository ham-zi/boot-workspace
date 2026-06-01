package com.kh.semi.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.semi.exception.DuplicateMemberIdException;
import com.kh.semi.exception.FailSignUpException;
import com.kh.semi.member.model.dao.MemberMapper;
import com.kh.semi.member.model.dto.MemberDto;
import com.kh.semi.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

	
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper; 
	private final PasswordEncoder passwordEncoder;
	
	/*
	 * SQL문 두 번 수행하는데, 셀렉1 인서트1번
	 * 현재 메소드는 Transactional을 붙이나 안붙이나 기능상의 차이는 존재하지 않음
	 * 
	 * 
	 * 
	 * 실무에서는 꼭 붙혀놓음 왜? 확장성(추후에 insert가 추가될수도 있음)
	 * 일반적으로 role을 table로 분리 해두는데, 왜? 한명의 사용자가 여러개의 role을 갖을 수 있음
	 * 기본적으로 회원가입은 두번 insert를 하는 경우가 많음
	 * 
	 * 추후에 메소드에 한 줄 추가 됐을 경우에도 원자성이 보장기 때문이다.
	 * 
	 * 
	 * 컨벤션, 유지보수 측면이 있다.
	 * 
	 * 컨벤션 : 관성으로 쓴다.
	 * 유지보수 : 안정성을 생각하자.
	 * 
	 *  커넥션, sqlSession 재활용한다.
	 * 
	 * 
	 * 
	 * 실습겸 숙제
	 * 오늘 했던 작업을 그대로 반복
	 * 
	 * 새 프로젝트 만들기
	 * 새 회원용 테이블 만들기
	 * 새 회원가입 기능 만들기 => 1절부터 4절까지 차근차근
	 * 
	 */
	
	
	@Transactional
	public void signUp(MemberDto member) {
		//아이디 중복검사
		int count = memberMapper.countByMemberId(member.getMemberId());
		if(count > 0) {
			throw new DuplicateMemberIdException("이미 존재하는 아이디입니다.");
		}
		
		//비밀번호 암호화
		String changePwd = passwordEncoder.encode(member.getMemberPwd());
		
		Member memberEntity = Member.builder().memberId(member.getMemberId())
					                  .memberName(member.getMemberName())
					                  .role("ROLE_USER")
					                  .memberPwd(changePwd)
					                  .build();
		
		//log.info("만들어보자 : {}", memberEntity);
		int result = memberMapper.signUp(memberEntity);
		// 일반적으로 => 성공/실패 여부를 반환 => 정수값~
		// 
		if(1 > result) {
			throw new FailSignUpException("잠시 후 다시 시도해주세요.");
		}
	}
}
