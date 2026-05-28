package com.kh.study.reply.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.study.reply.model.dao.ReplyMapper;
import com.kh.study.reply.model.dto.ReplyDto;

@Service
public class ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	
	public List<ReplyDto> findAll(){
		return replyMapper.findAll();
	}
	
	public ReplyDto findByNo(Long replyNo) {
		return replyMapper.findByNo(replyNo);
	}
	
	
}
