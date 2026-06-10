package com.kh.semi.member.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.semi.auth.model.vo.CustomUserDetails;
import com.kh.semi.member.model.dto.MemberDto;
import com.kh.semi.member.model.dto.UpdatePasswordDto;
import com.kh.semi.member.model.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@PostMapping
	public ResponseEntity<Void> signUp(@Valid @RequestBody MemberDto member) {
		//log.info("body 데이터 : {}", member);
		memberService.signUp(member);
		return ResponseEntity.status(201).build();
	}
	
	@PatchMapping
	public ResponseEntity<Void> changePassword(@AuthenticationPrincipal CustomUserDetails user, @Valid @RequestBody UpdatePasswordDto upd){
		//1. 아이디 , 변경전 비밀번호, 변경 후 비밀번호
		//2. 비밀번호값에 대한 유효성 검증
		//3. 지금 요청을 보낸 사용자가 입력한 기존의 비밀번호가 DB에 저장된 것과 매칭이 잘되는지 확인
		//4. 새로 입력한 비밀번호에 대한 암호화 작업
		//5. DB에가서 UPDATE
		log.info("요청이 잘 넘어오는가 {} / {}", user, upd);
		memberService.changePassword(user, upd);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteByPassword(@AuthenticationPrincipal CustomUserDetails user, @RequestBody Map<String, String> password){
		memberService.deleteByPassword(password.get("password"), user);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
