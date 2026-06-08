package com.kh.semi.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class Board {
	private Long boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String fileUrl;
	private Date createDate;
	private String status;
}
