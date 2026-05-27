package com.kh.study.member.model.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.study.member.model.dto.MemberDto;
import com.kh.study.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("members")
@RestController
public class MemberController {
	
	@Autowired
	private MemberService service; 
	
	@PostMapping
	public Map<String, String> enroll(@RequestBody MemberDto member) {
		int result = service.enroll(member);
		if(result == 1) {
			return Map.of("msg", "성공");
		}
		return Map.of("msg","실패");
	}
}
