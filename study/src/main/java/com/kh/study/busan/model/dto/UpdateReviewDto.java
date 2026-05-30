package com.kh.study.busan.model.dto;

public class UpdateReviewDto {
	private String content;
	private String updateContent;
	private Long ucSeq;
	public UpdateReviewDto(String content, String updateContent) {
		super();
		this.content = content;
		this.updateContent = updateContent;
	}
	public UpdateReviewDto() {
		super();
	}
	public Long getUcSeq() {
		return ucSeq;
	}
	public void setUcSeq(Long ucSeq) {
		this.ucSeq = ucSeq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpdateContent() {
		return updateContent;
	}
	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}
	public UpdateReviewDto(String content, String updateContent, Long ucSeq) {
		super();
		this.content = content;
		this.updateContent = updateContent;
		this.ucSeq = ucSeq;
	}
	
	
}
