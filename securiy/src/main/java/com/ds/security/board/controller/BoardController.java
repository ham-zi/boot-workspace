package com.ds.security.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ds.security.auth.model.vo.CustomUserDetails;
import com.ds.security.board.model.dto.BoardDto;
import com.ds.security.board.model.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@Slf4j
public class BoardController {

	private final BoardService boardService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestParam(name="files", required=false)List<MultipartFile>files, @Valid BoardDto board, @AuthenticationPrincipal CustomUserDetails user){
		
		boardService.save(user, files, board);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(name="page")int page) {
		boardService.findAll(page);
		
		return ResponseEntity;
	}
}
