package com.ds.security.board.model.vo;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Board {
		private Long boardNo;
		private Long refMno;
		private String boardTitle;
		private String boardContent;
		private Date createDate;
		private String axistFile;//M:Main이미지/ C:Common이미지 
		private Long views;
		private String status;

}
