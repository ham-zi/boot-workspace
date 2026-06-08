package com.ds.security.exception.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ErrorResponse {
	private int code;
	private String message;
	private Object data;
}
