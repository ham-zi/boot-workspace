package com.kh.study.reply.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.study.reply.model.dto.ReplyDto;

@Mapper
public interface ReplyMapper {
	
	@Select("SELECT * FROM REPLY ORDER BY REPLY_NO DESC")
	List<ReplyDto> findAll();
	
	@Select("SELECT * FROM REPLY WHERE REPLY_NO = #{replyNo}")
	ReplyDto findByNo(Long replyNo);
}
