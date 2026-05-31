package com.ds.legacy.board.model.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ds.legacy.board.model.dao.BoardMapper;
import com.ds.legacy.board.model.dto.BoardDto;
import com.ds.legacy.member.exception.NotFoundMemberException;
import com.ds.legacy.member.model.dao.MemberMapper;
import com.ds.legacy.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardMapper mapper;
	private final MemberService memberService;
	
	public List<BoardDto> findAll() {
		return mapper.findAll();
	}

	public BoardDto findByNo(Long boardNo) {
		return mapper.findByNo(boardNo);
	}
	
	public void save(BoardDto board) {
		validMember(board.getRefMno());
		mapper.save(board);
	}
	
	private void validMember(Long userNo) {
		if (memberService.findByNo(userNo) == null) {
			throw new NotFoundMemberException("없는 유저입니다.");
		}

	}
}
