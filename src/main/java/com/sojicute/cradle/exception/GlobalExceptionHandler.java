package com.sojicute.cradle.exception;


import com.sojicute.cradle.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Response> handleConstraintValidationException(
//            ConstraintViolationException e) {
//        Response response = new Response();
//        for (ConstraintViolation violation : e.getConstraintViolations()) {
//            response.getViolations().add(
//                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
//        }
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        Response response = new Response();
//
//        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
//            response.getViolations().add(
//                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
//        }
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//

//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(final UsernameNotFoundException exception) {
//        final ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//        errorResponse.setException(exception.getClass().getSimpleName());
//        errorResponse.setMessage(exception.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<Response> handleEntityNotFoundException(EntityNotFoundException ex) {
//        Response response = new Response(ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler({AuthenticationException.class})
//    public final ResponseEntity<Response> handleAccessDeniedException(AccessDeniedException ex){
//        Response response = new Response(ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<Response> handleException(AccessDeniedException ex) {
//        Response response = new Response(ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Response> handleGlobalException(Exception ex) {
//        Response response = new Response(ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }


}
