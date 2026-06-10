package com.kh.semi.comment.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.semi.auth.model.vo.CustomUserDetails;
import com.kh.semi.board.model.service.BoardService;
import com.kh.semi.comment.model.dao.CommentMapper;
import com.kh.semi.comment.model.dto.CommentDto;
import com.kh.semi.comment.model.vo.Comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
	private final CommentMapper commentMapper;
	private final BoardService boardService;
	
	@Transactional
	public void save(CommentDto comment, CustomUserDetails user) {
		boardService.findByBoardNo(comment.getRefBoardNo());
		Comment c = Comment.builder()
				           .CommentWriter(user.getUsername())
				           .commentContent(comment.getCommentContent())
				           .refBoardNo(comment.getRefBoardNo())
				           .build();
		commentMapper.save(c);
	}

	public List<CommentDto> findAll(Long boardNo) {
		boardService.findByBoardNo(boardNo);
		return commentMapper.findAll(boardNo);
	}
	
}
