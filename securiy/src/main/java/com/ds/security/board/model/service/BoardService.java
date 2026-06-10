package com.ds.security.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ds.security.auth.model.vo.CustomUserDetails;
import com.ds.security.board.model.dao.BoardMapper;
import com.ds.security.board.model.dto.BoardDto;
import com.ds.security.board.model.vo.Board;
import com.ds.security.file.model.dto.FileDto;
import com.ds.security.file.model.service.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardService {
	
	private final BoardMapper boardMapper;
	private final FileService fileService;
	
	
	
	public List<BoardDto> findAll(int page){
		
	}
	
	@Transactional
	public void save(CustomUserDetails user, List<MultipartFile>files, BoardDto boardDto) {
		BoardDto board = replaceBoard(boardDto);
		Board boardVo = Board.builder().boardTitle(board.getBoardTitle())
		               .boardContent(board.getBoardContent())
		               .axistFile(existFile(files))
		               .refMno(user.getUserNo())
		               .build();
		boardMapper.save(boardVo);
		log.info("{},{}",files,boardVo);
		for(int i=0; i<files.size() ; i++) {
			FileDto fileDto = new FileDto();
			if(i == 0) {
				fileDto.setFileLevel("M");
			} else {
				fileDto.setFileLevel("C");
			}
			String changeName = fileService.rename(files.get(i));
			fileDto.setRefBno(boardVo.getBoardNo());
			fileDto.setOriginName(files.get(i).getOriginalFilename());
			fileDto.setChangeName(changeName);
			fileDto.setFilePath(fileService.fileTransferTo(files.get(i), changeName));
			
			boardMapper.saveFile(fileDto);
			
		}
		
		     
	}
	
	private String existFile(List<MultipartFile>files) {
		if(files.isEmpty() || files.get(0).getOriginalFilename() == null) {
			return "N";
		}
		return "Y";
	}
	
	private BoardDto replaceBoard(BoardDto board) {
		String title = titleReplace(board.getBoardTitle());
		String content = contentReplace(board.getBoardContent());
		board.setBoardTitle(title);
		board.setBoardContent(content);
		return board;
	}
	
	private String titleReplace(String boardTitle) {
		boardTitle.replaceAll("&", "&amp;");
		boardTitle.replaceAll("<", "&lt;");
		boardTitle.replaceAll(">", "&gt;");
		boardTitle.replaceAll("\"", "&quot;");
		return boardTitle;
	}
	private String contentReplace(String boardContent) {
		boardContent.replaceAll("&", "&amp;");
		boardContent.replaceAll("<", "&lt;");
		boardContent.replaceAll(">", "&gt;");
		boardContent.replaceAll("\"", "&quot;");
		return boardContent;
	}
}
