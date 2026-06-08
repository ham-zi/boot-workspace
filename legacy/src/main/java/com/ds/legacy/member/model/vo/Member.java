package com.ds.legacy.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class Member {
	  private Long userNo;
	  private String userId;   
	  private String userPwd;
	  private String userName;            
	  private String email;
	  private Date enrollDate;
	  private Date modifyDate;
	  private String status;
}
