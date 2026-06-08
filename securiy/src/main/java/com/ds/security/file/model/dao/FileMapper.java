package com.ds.security.file.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ds.security.file.model.dto.FileDto;

@Mapper
public interface FileMapper {
	void saveFile(FileDto file);
}
