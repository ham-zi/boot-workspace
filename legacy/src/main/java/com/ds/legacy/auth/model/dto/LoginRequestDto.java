package com.ds.legacy.auth.model.dto;

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
public class LoginRequestDto {
	  @NotBlank(message="아이디를 입력해주세요.")
	  @Pattern(regexp="^[a-zA-Z0-9]*$", message="아이디는 영어/숫자만 가능합니다.")
	  @Size(min=4, max=20, message="아이디는 4글자 이상, 20글자 이하만 가능합니다. ")
	  private String userId;   
	  @NotBlank(message="비밀번호를 입력해주세요")
	  @Pattern(regexp="^[a-zA-Z0-9]*$", message="비밀번호는 영어/숫자만 가능합니다.")
	  @Size(min=4, max=20, message="비밀번호는 4글자이상, 20글자 이하만 가능합니다.")
	  private String userPwd;
}
