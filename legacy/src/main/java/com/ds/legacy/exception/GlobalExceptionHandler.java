package com.ds.legacy.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ds.legacy.exception.member.DuplicationIdException;
import com.ds.legacy.exception.member.FailMemberSaveException;
import com.ds.legacy.exception.member.NotFoundIdException;
import com.ds.legacy.exception.model.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundIdException.class)
	public ResponseEntity<ErrorResponse> handlerNotFoundId(NotFoundIdException e) {
		return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage(), null));
	}
	
	@ExceptionHandler(DuplicationIdException.class)
	public ResponseEntity<ErrorResponse> handlerDuplicationId(DuplicationIdException e) {
		return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage(), null));
	}
	
	@ExceptionHandler(FailMemberSaveException.class)
	public ResponseEntity<ErrorResponse> hanlderFailMemberSave(FailMemberSaveException e) {
		return ResponseEntity.badRequest().body(new ErrorResponse(400,e.getMessage(), null));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValid(MethodArgumentNotValidException e) {
		
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(err ->{
			errors.put(err.getField(), err.getDefaultMessage());			
		});
		return ResponseEntity.badRequest().body(new ErrorResponse(400,"잘못된 요청입니다.",errors));
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerUsernameNotFound(UsernameNotFoundException e) {
		return ResponseEntity.badRequest().body(new ErrorResponse(400,e.getMessage(),null));
	}
	
}
