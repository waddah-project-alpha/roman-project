package com.adobe.integer_to_roman.controlleradvice;

import com.adobe.integer_to_roman.exceptions.InvalidIntegerToRomanInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalControllerExceptionHandling {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidInput(InvalidIntegerToRomanInputException ex)
    {
        return new ResponseEntity<>("Invalid input received. Error: " + ex.getMessage(),
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex)
    {
        return new ResponseEntity<>("An unexpected error has occurred. Please try again or contact our team",
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
    }
}
