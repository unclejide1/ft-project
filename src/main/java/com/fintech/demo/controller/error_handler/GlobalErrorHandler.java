package com.fintech.demo.controller.error_handler;


import com.fintech.demo.dto.ApiResponseJSON;
import com.fintech.demo.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(basePackages = "com.fintech.demo.controller")
public class GlobalErrorHandler  {

    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    public ResponseEntity<ApiResponseJSON<String>> handleException(Exception exception) {
        log.info("Exception: {}", ExceptionUtils.getStackTrace(exception));
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>("Sorry, currently unable to process request at the moment.", exception.getMessage(), false);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ApiResponseJSON<String>> handleMissingServletRequestParameterException(Exception exception) {
        log.info("Exception: {}", exception.getLocalizedMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(exception.getLocalizedMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ApiResponseJSON<String>> handleBadCredentialsException(Exception exception) {
        log.info("Exception: {}", exception.getLocalizedMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(exception.getLocalizedMessage(), "invalid username or password", false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<ApiResponseJSON<String>> handleExpiredException(Exception exception) {
        log.info("Exception: {}", exception.getLocalizedMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(exception.getLocalizedMessage(), "Expired JWT", false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiResponseJSON<Object>> handleUnauthorisedOperationException(AccessDeniedException exception) {
        return new ResponseEntity<>(new ApiResponseJSON<>("Sorry, you are not authorized to do this."), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    public ResponseEntity<ApiResponseJSON<String>> handleMissingServletRequestPartException(Exception exception) {
        log.info("error message: {}", exception.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>("Request validation failure. Please check your request data.");
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {UnsupportedOperationException.class})
    public ResponseEntity<ApiResponseJSON<String>> handleMissingServletRequestPartEx(Exception exception) {
        log.info("error message: {}", exception.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>("method not allowed");
        return new ResponseEntity<>(apiResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleHttpRequestMethodNotSupported(HttpServletRequest request, Exception e) {
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponseJSON<String>> handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException exception) {
        String errorMessage = "Request validation failure. Please check your request data.";
        BindingResult result = exception.getBindingResult();
        FieldError fieldError = result.getFieldError();
        if(fieldError != null) {
            errorMessage = fieldError.getDefaultMessage();
        }
        log.info("error message: {}", errorMessage);
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(errorMessage);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleBadRequestException(BadRequestException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessLogicConflictException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleBusinessLogicConflictException(BusinessLogicConflictException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RequestForbiddenException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleRequestForbiddenException(RequestForbiddenException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleNotFoundException(NotFoundException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }//

    @ExceptionHandler(FailedPreConditionException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleFailedPreConditionException(FailedPreConditionException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(UnauthorisedAccessException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleUnauthorisedAccessException(UnauthorisedAccessException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }

}
