package com.kh.semi.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@PreAuthorize("hasRole('ADMIN')") // 세부적인 역할 구분이 가능하다
	@GetMapping
	public String findAllMembers() {
		// 디비에서 전체조회 한 정보
		return "회원 전체 정보";
	}
	
	
	//작성
	
	//조회
	
}
