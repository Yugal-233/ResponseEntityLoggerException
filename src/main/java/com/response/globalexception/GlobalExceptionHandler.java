package com.response.globalexception;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.response.exception.UserNotFoundException;
import com.response.exception.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {
		
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExption(Exception e){
		return new ResponseEntity<>("error occurred: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("resource not found"+ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed: " + ex.getMessage());
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException ex){
    	return new ResponseEntity<>("User not found :"+ex.getMessage(),HttpStatus.NOT_FOUND);
    }
}
