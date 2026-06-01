package com.kh.fruit.user.model.vo;

import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class User {
	private Long userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String role;
	private Date createDate;
	private String status;
}
