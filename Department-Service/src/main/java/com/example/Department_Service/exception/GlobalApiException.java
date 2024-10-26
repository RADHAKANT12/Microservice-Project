package com.example.Department_Service.exception;

import com.example.Department_Service.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalApiException {

    @ExceptionHandler(Exception.class) // This handles all exceptions
    public ResponseEntity<ErrorDetails> handleGlobalApiException(Exception exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                webRequest.getDescription(false),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
