package com.ds.security.board.model.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDto {
	private Long boardNo;
	private Long refMno;
	@NotBlank(message="제목이 비어있습니다.")
	private String boardTitle;
	@NotBlank(message="내용이 비어있습니다.")
	private String boardContent;
	private Date createDate;
	private String axistFile;//M:Main이미지/ C:Common이미지 
	private Long views;
	private String status;
}
