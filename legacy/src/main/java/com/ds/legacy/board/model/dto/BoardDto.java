package com.ds.legacy.board.model.dto;

import java.sql.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotNull @Min(1)
	private Long refMno;
	@NotBlank
	private String boardTitle;
	@NotBlank
	private String boardContent;
	private Date createDate;
	private Long views;
	private String status;
}
