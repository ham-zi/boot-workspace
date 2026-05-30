package com.kh.study.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.study.util.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e){
		Map<String,String> errors = new HashMap();
		
		e.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(),err.getDefaultMessage())  );
		
		ErrorResponse er = new ErrorResponse(4, "잘못된 파라미터", errors);
		System.out.println(e.getBindingResult().getFieldErrors());
		
		return ResponseEntity.badRequest().body(er);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException e) {
        // "getRes.arg0: 1 이상이어야 합니다"와 같은 메시지를 추출하여 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException e) {
		ErrorResponse er = new ErrorResponse(44, e.getMessage(), null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
	}
}
