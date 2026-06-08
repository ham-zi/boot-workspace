package com.ds.security.board.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ds.security.board.model.vo.Board;
import com.ds.security.file.model.dto.FileDto;

@Mapper
public interface BoardMapper {

	void save(Board board);
	void saveFile(FileDto file);
}
