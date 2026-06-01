package com.kh.fruit.exception;

public class DuplicateUserIdException extends RuntimeException {
	public DuplicateUserIdException(String message) {
		super(message);
	}
}
