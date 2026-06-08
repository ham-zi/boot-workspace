package com.ds.legacy.member.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.legacy.member.model.dto.MemberDto;
import com.ds.legacy.member.model.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
@CrossOrigin("*")
@Tag(name="회원 정보 API", description="회원 정보에 대한 CRUD API입니다.")
public class MemberController {
	
	private final MemberService service;
	
	@ApiResponses({
		@ApiResponse(responseCode="200", description="성공성공!"),
		@ApiResponse(responseCode="404", description="조회 실패...")
	})
	@GetMapping
	@Operation(summary="회원 전체 조회", description="회원 전체 정보를 조회합니다.")
	public ResponseEntity<List<MemberDto>> findAll(){
		List<MemberDto> members = service.findAll();
		if(members.isEmpty()) {
			
		}
		return ResponseEntity.ok(members);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MemberDto> findByNo(@Parameter(description="유저 번호", example="1")@PathVariable(value="id")Long userNo){
		MemberDto member = service.findByNo(userNo);
		return ResponseEntity.ok(member);
	}
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody@Valid MemberDto memberDto){
		log.info("-0-----");
		service.save(memberDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("생성성공");
	}
	
	
	
}
