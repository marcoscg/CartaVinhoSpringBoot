package com.mardeveloper.wine.controller.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mardeveloper.wine.model.StandardError;
import com.mardeveloper.wine.service.exception.DatabaseException;
import com.mardeveloper.wine.service.exception.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), null, request.getRequestURI());		
		return ResponseEntity.status(status).body(err);
	};
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), null, request.getRequestURI());		
		return ResponseEntity.status(status).body(err);
	};
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> databaseInt(DataIntegrityViolationException e, HttpServletRequest request){
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), null, request.getRequestURI());		
		return ResponseEntity.status(status).body(err);
	};
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validate(MethodArgumentNotValidException e, HttpServletRequest request){
		String error = "Validate error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Map<String, String> details = new HashMap<>();
	    e.getBindingResult().getAllErrors().forEach(erro -> {
            String fieldName = ((FieldError) erro).getField();
            String errorMessage = erro.getDefaultMessage();
            details.put(fieldName, errorMessage);
        });		
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, error, details, request.getRequestURI());	
		
		return ResponseEntity.status(status).body(err);		
	};	
	
	
}
