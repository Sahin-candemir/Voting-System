package com.shnc.VotingSystem.exceptions;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.shnc.VotingSystem.dto.ErrorDetails;

@RestControllerAdvice
public class GlobalExceptionAdvice {

	@ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<String>> handleBadRequest(MethodArgumentNotValidException e) {
        List<String> errorMessages = e.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(toList());
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
 
 @ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception,WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
 @ExceptionHandler(UsernameAllreadyExists.class)
	public ResponseEntity<ErrorDetails> handleBlogAPIException(UsernameAllreadyExists exception,WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	
 } 
}
