package com.ds.security.member.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Member {
	private Long userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String role;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
}
