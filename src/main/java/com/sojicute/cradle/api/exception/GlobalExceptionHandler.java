package com.sojicute.cradle.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        Response response = new Response(ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//
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
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Response> handleUserNotFoundException(final UsernameNotFoundException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> handleException(AccessDeniedException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGlobalException(Exception ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
