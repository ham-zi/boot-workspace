package com.ds.security.auth.model.dto;

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
public class LoginDto {
	@NotBlank(message="아이디를 입력해주세요")
	@Size(min=4, max=20, message="아이디는 글자 4개 이상, 20개 이하 입력해주세요.")
	@Pattern(regexp="^[a-zA-Z0-9]*$", message="아이디는 영어와 숫자만 입력 가능합니다.")
	private String userId;
	@NotBlank(message="비밀번호를 입력해주세요")
	@Size(min=4, max=20, message="비밀번호는 글자 4개 이상, 20개 이하 입력해주세요.")
	@Pattern(regexp="^[a-zA-Z0-9]*$", message=" 비밀번호는 영어와 숫자만 입력 가능합니다.")
	private String userPwd;
}
