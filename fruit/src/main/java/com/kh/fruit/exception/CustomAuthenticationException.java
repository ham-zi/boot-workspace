package com.kh.fruit.exception;

public class CustomAuthenticationException extends RuntimeException {
	public CustomAuthenticationException(String message) {
		super(message);
	}
}
