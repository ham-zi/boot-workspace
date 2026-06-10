package com.kh.semi.api.model.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
	private int code;
	private String message;
	private T data;
	
	/*
	 * 제네릭이 오브젝트보다 좋은 점
	 * 타입 안정성 => Object받으면 꺼내 쓸 때 캐스팅을 강제로 해야함, 캐스팅이 틀려도 컴파일러가 못잡음
	 * IDE 자동완성 / 가독성 ResponseEntity<ApiResponse<LoginResponse>>
	 * 
	 */
	
	//정적 팩토리 메서드
	
	//200성공 응답
	public static <T> ApiResponse<T> success(T data){
		return new ApiResponse<>(200, "요청에 성공했습니다.", data);
	}
	
	public static <T> ApiResponse<T> success(String message, T data) {
		return new ApiResponse<>(200, message, data);
	}
	
	// 201성공 응답
	public static <T> ApiResponse<T> created(T data){
		return new ApiResponse<>(201,"요청에 성공했습니다.",data);
	}
	
	public static <T> ApiResponse<T> created(String message, T data) {
		return new ApiResponse<>(201, message, data);
	}
	
	// 204성공 응답
	public static <T> ApiResponse<T> noContent(T data) {
		return new ApiResponse<>(204, "요청에 성공했습니다.", data);
	}
	
}
