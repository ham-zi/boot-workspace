package com.ds.legacy.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ds.legacy.board.model.dto.BoardDto;

@Mapper
public interface BoardMapper {
	@Select("SELECT * FROM DS_BOARD WHERE STATUS='Y' ORDER BY BOARD_NO DESC")
	public List<BoardDto> findAll();

	@Select("SELECT * FROM DS_BOARD WHERE STATUS='Y' AND BOARD_NO = #{boardNo}")
	public BoardDto findByNo(Long boardNo);
	
	@Insert("INSERT INTO DS_BOARD VALUES (SEQ_DS_BNO.NEXTVAL, #{refMno}, #{boardTitle}, #{boardContent}, SYSDATE, 0, 'Y')")
	public void save(BoardDto board);
}
