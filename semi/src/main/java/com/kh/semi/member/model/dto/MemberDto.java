package com.kh.semi.member.model.dto;

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
	@Pattern(regexp = "^[a-zA-Z0-9]*$",message="아이디 값은 영어/ 숫자만 가능합니다.")
	@Size(min = 4, max = 20, message="아이디 값은 4글자 이상, 20글자 이하만 사용할 수있습니다.")
	@NotBlank(message="아이디 값은 비어있을 수 없습니다.")
	private String memberId;	
	@Pattern(regexp = "^[a-zA-Z0-9]*$",message="비밀번호 값은 영어/ 숫자만 가능합니다.")
	@Size(min = 4, max = 20, message="비밀번호 값은 4글자 이상, 20글자 이하만 사용할 수있습니다.")
	@NotBlank(message="아이디 값은 비어있을 수 없습니다.")
	private String memberPwd;
	@Size(min = 2, max = 30, message="이름은 2글자 이상, 30글자 이하만 사용할 수 있습니다.")
	@NotBlank
	private String memberName;
	private String role;
	private String status;
}
