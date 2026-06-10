package com.kh.semi.comment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.semi.api.model.dto.vo.ApiResponse;
import com.kh.semi.auth.model.vo.CustomUserDetails;
import com.kh.semi.comment.model.dto.CommentDto;
import com.kh.semi.comment.model.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
	private final CommentService commentService;
	
	//댓글 작성
	//
	// 정적 팩토리 메서드
	// **이름이 생긴다.
	// 
	
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody CommentDto comment,@AuthenticationPrincipal CustomUserDetails user){
		commentService.save(comment, user);
		//ApiResponse<Void> ar = new ApiResponse(201, "하지만", null);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(null));
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<CommentDto>>> findAll(@RequestParam(name="boardNo")Long boardNo){
		List<CommentDto> comments = commentService.findAll(boardNo);
		return ResponseEntity.ok(ApiResponse.success(comments));
	}
	
	
}

