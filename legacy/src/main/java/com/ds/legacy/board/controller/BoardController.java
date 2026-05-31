package com.ds.legacy.board.controller;

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

import com.ds.legacy.board.model.dto.BoardDto;
import com.ds.legacy.board.model.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BoardController {
	
	private final BoardService service;
	
	@GetMapping
	public ResponseEntity<List<BoardDto>> findAll(){
		
		List<BoardDto> boards = service.findAll();
		
		return ResponseEntity.ok(boards);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BoardDto> findByNo(@PathVariable(value="id")Long boardNo){
		BoardDto board = service.findByNo(boardNo);
		return ResponseEntity.ok(board);
	}
	
	
	@PostMapping()
	public ResponseEntity<String> save(@Valid @RequestBody BoardDto board){
		service.save(board);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
}
