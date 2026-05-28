package com.kh.study.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.study.member.model.dto.MemberDto;
import com.kh.study.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
@CrossOrigin("*")
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
	
	@GetMapping
	public List<MemberDto> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public MemberDto findById(@PathVariable(value="id")String userId) {
		return service.findById(userId);
	}
	
	
	
}
