package com.kh.fruit.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.fruit.exception.model.dto.ErrorResponse;

@RestControllerAdvice
public class GrobalExceptionHandler {

	@ExceptionHandler(DuplicateUserIdException.class)
	public ResponseEntity<ErrorResponse> handlerDuplicateUserId(DuplicateUserIdException e){
		ErrorResponse err = new ErrorResponse(400, e.getMessage(), null);
		return ResponseEntity.badRequest().body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValid(MethodArgumentNotValidException e){
		 
		Map<String, String> errors = new HashMap();
		e.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
		
		return ResponseEntity.badRequest().body(new ErrorResponse(400, "유효하지 않은 요청입니다.", errors));
	}
	
	@ExceptionHandler(NotFoundUserIdException.class)
	public ResponseEntity<ErrorResponse> handlerNotFoundUserId(NotFoundUserIdException e) {
		return ResponseEntity.badRequest().body(new ErrorResponse(400,e.getMessage(), null));
	}
	
	@ExceptionHandler(CustomAuthenticationException.class)
	public ResponseEntity<ErrorResponse> handlerCustomAuthentication(CustomAuthenticationException e){
		return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage(), null));
	}
	
}
