package carpelune.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import carpelune.exceptions.EmailConflictException;
import carpelune.exceptions.ErrorResponse;
import carpelune.exceptions.PasswordMismatchException;
import carpelune.exceptions.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePassowrdMismatchException(PasswordMismatchException err) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        	.body(new ErrorResponse(err.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
	
	@ExceptionHandler(EmailConflictException.class)
	public ResponseEntity<ErrorResponse> handleEmailConflictException(EmailConflictException err){
		return ResponseEntity.status(HttpStatus.CONFLICT)
			.body(new ErrorResponse(err.getMessage(), HttpStatus.CONFLICT.value()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException err){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new ErrorResponse(err.getMessage(), HttpStatus.NOT_FOUND.value()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e){
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
			.body(new ErrorResponse("Service Unavailable.", HttpStatus.SERVICE_UNAVAILABLE.value()));
	}
	
}
