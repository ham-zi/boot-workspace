package com.ds.security.member.model.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	private Long userNo;
	@NotBlank(message="아이디를 입력해주세요")
	@Size(min=4, max=20, message="아이디는 글자 4개 이상, 20개 이하 입력해주세요.")
	@Pattern(regexp="^[a-zA-Z0-9]*$", message="아이디는 영어와 숫자만 입력 가능합니다.")
	private String userId;
	@NotBlank(message="비밀번호를 입력해주세요")
	@Size(min=4, max=20, message="비밀번호는 글자 4개 이상, 20개 이하 입력해주세요.")
	@Pattern(regexp="^[a-zA-Z0-9]*$", message=" 비밀번호는 영어와 숫자만 입력 가능합니다.")
	private String userPwd;
	@NotBlank(message="이름을 입력해주세요")
	@Size(min=2, max=20, message="이름는 글자 2개 이상, 20개 이하 입력해주세요.")
	@Pattern(regexp="^[a-zA-Z0-9가-힣]*$", message="이름은 영어와 숫자, 한글만 입력 가능합니다.")
	private String userName;
	@NotBlank(message="이메일을 입력해주세요")
	private String email;
	private String role;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
}
