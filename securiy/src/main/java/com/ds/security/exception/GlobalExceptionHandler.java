package com.ds.security.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ds.security.exception.model.vo.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotFoundIdException.class)
	public ResponseEntity<ErrorResponse> handlerNotFoundId(NotFoundIdException e){
		return ResponseEntity.badRequest().body(new ErrorResponse(400,e.getMessage(),null));
	}
}
