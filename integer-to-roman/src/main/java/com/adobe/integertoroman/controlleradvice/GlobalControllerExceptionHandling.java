package com.adobe.integertoroman.controlleradvice;

import com.adobe.integertoroman.exceptions.InvalidIntegerToRomanInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller advice which handles (business) exceptions thrown by a controller. Though this is not the only way to
 * handle exceptions in Spring, this would arguable be the cleanest way to handle a multitude of exceptions in a
 * real world app
 */
@ControllerAdvice
@RestController
public class GlobalControllerExceptionHandling {
    @ExceptionHandler(InvalidIntegerToRomanInputException.class)
    public ResponseEntity<String> handleInvalidInput(InvalidIntegerToRomanInputException ex) {
        return new ResponseEntity<>("Invalid input received. Error: " + ex.getMessage(),
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
    }
}
