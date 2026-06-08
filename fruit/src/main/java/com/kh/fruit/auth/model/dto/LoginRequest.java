package com.kh.fruit.auth.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class LoginRequest {
	@Size(min=4, max=20, message="아이디의 글자는 4개이상, 20개이하로 작성해주세요.")
	@Pattern(regexp="^[a-zA-Z0-9]*$", message="아이디는 숫자,영어만 작성 가능합니다.")
	@NotBlank(message="아이디가 비어있습니다.")
	private String userId;
	@Size(min=4, max=20, message="비밀번호의 글자는 4개이상, 20개이하로 작성해주세요.")
	@Pattern(regexp="^[a-zA-Z0-9]*$", message="비밀번호는 숫자,영어만 작성 가능합니다.")
	@NotBlank(message="비밀번호가 비어있습니다.")
	private String userPwd;
}
