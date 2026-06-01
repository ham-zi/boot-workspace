package com.kh.fruit.user.model.dto;

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
public class UserDto {
	private Long userNo;
	@Size(min=4, max=20, message="아이디의 글자는 4개이상, 20개이하로 작성해주세요.")
	@Pattern(regexp="^[a-zA-Z0-9]*$", message="아이디는 숫자,영어만 작성 가능합니다.")
	@NotBlank(message="아이디가 비어있습니다.")
	private String userId;
	@Size(min=4, max=20, message="비밀번호의 글자는 4개이상, 20개이하로 작성해주세요.")
	@Pattern(regexp="^[a-zA-Z0-9]*$", message="비밀번호는 숫자,영어만 작성 가능합니다.")
	@NotBlank(message="비밀번호가 비어있습니다.")
	private String userPwd;
	@Size(min=2, max=20, message="이름의 글자는 2개이상, 20개이하로 작성해주세요.")
	@Pattern(regexp="^[a-zA-Z0-9가-힣]*$", message="이름은 한글,숫자,영어만 작성 가능합니다.")
	@NotBlank(message="이름이 비어있습니다.")
	private String userName;
	private String role;
	private Date createDate;
	private String status;
}
