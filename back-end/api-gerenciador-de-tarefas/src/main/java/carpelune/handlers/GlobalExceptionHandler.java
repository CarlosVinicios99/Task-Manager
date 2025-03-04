package carpelune.handlers;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException err){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new ErrorResponse(err.getMessage(), HttpStatus.NOT_FOUND.value()));
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body(new ErrorResponse("E-mail or password incorrect", HttpStatus.UNAUTHORIZED.value()));
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body(new ErrorResponse("Authentication Failed!", HttpStatus.UNAUTHORIZED.value()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e){
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
			.body(new ErrorResponse("Service Unavailable.", HttpStatus.SERVICE_UNAVAILABLE.value()));
	}
	
}
