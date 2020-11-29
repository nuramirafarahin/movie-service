package com.example.movie.exception;

import com.example.movie.models.exception.ErrorMessage;
import com.example.movie.models.exception.ValidationResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex) {
        logger.error(ex.getMessage());

        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalExceptionHandler(Exception ex) {
        logger.error(ex.getMessage());

        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> handleNotValidException(MethodArgumentNotValidException e) {

        ValidationResults validationResults = new ValidationResults();
        e.getBindingResult().getAllErrors()
                .stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .forEach(fieldError -> validationResults.addResult(fieldError.getField(), fieldError.getDefaultMessage()));
        return validationResults.getResults();
    }
}
