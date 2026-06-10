package com.ds.security.response.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiResponse<T> {
	private int code;
	private String message;
	private T data;
	//200리폰스
	public static <T> ApiResponse<T> success(String message, T data){
		return new ApiResponse<>(200, message, data);
	}
	
	//201리스폰스
	public static <T> ApiResponse<T> created(String message, T data) {
		return new ApiResponse<>(201, message, data);
	}
	
	//204리스폰스
	public static <T> ApiResponse<T> noContent(String message, T data) {
		return new ApiResponse<>(204, message, data);
	}
}




