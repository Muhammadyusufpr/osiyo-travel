package com.osiyotravel.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import perfetto.dto.detail.ApiResponse;
import perfetto.exception.*;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({ItemAlreadyExistsException.class, ItemNotFoundException.class, AppBadRequestException.class})
    public ResponseEntity<ApiResponse<?>> handleBadRequestException(RuntimeException e) {
        return ResponseEntity.ok(new ApiResponse<>(e.getMessage(), 400, true));
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ApiResponse<?>> handleUserNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.ok(new ApiResponse<>(e.getMessage(), 400, true));
    }

    @ExceptionHandler({TokenNotValidException.class, BadCredentialsException.class})
    public ResponseEntity<?> handleTokenException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

//    @ExceptionHandler({ClassCastException.class})
//    public ResponseEntity<?> handleNotFoundTokenException() {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Found Token!");
//    }

    @ExceptionHandler({AppForbiddenException.class})
    public ResponseEntity<?> handleForbiddenException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler({AppNotAcceptableException.class})
    public ResponseEntity<?> handleNotAcceptableException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }


}
