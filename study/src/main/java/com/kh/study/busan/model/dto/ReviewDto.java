package com.kh.study.busan.model.dto;

import java.sql.Date;

public class ReviewDto {
	private Long ucSeq;
	private String content;
	private Integer rating;
	private Date createDate;
	public ReviewDto(Long ucSeq, String content, Integer rating, Date createDate) {
		super();
		this.ucSeq = ucSeq;
		this.content = content;
		this.rating = rating;
		this.createDate = createDate;
	}
	public ReviewDto() {
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
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "ReviewDto [ucSeq=" + ucSeq + ", content=" + content + ", rating=" + rating + ", createDate="
				+ createDate + "]";
	}
	
}
