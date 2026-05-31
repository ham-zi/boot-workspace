package com.ds.legacy.member.model.dto;

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
public class MemberDto {
	  @NotNull @Min(1)
	  private Long userNo;
	  @NotBlank
	  private String userId;   
	  @NotBlank
	  private String userPwd;
	  @NotBlank
	  private String userName;            
	  @NotBlank
	  private String email;
	  private Date enrollDate;
	  private Date modifyDate;
	  private String status;
}
