package com.kh.study.reply.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.study.reply.model.dto.ReplyDto;
import com.kh.study.reply.model.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("api/replies")
@CrossOrigin("*")
@RestController
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@GetMapping
	public List<ReplyDto> findAll(){
		return replyService.findAll();
	}
	
	@GetMapping("/{id}")
	public ReplyDto findByNo(@PathVariable(name="id")Long replyNo) {
		return replyService.findByNo(replyNo);
	}
	
}
