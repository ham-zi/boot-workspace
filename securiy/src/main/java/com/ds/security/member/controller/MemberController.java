package com.ds.security.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.security.member.model.dto.MemberDto;
import com.ds.security.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	private final MemberService service;
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody MemberDto member) {
		service.save(member);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
