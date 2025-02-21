package com.isi.maven.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<APIException> handleRequestException(RequestException e) {
        APIException exception = new APIException(e.getMessage(), e.getStatus(), LocalDateTime.now());
        return new ResponseEntity<>(exception, e.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException exp) {
        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(null, null, "Validation failed", errors, null));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<APIException> handleEntityNotFoundException(EntityNotFoundException e) {
        APIException exception = new APIException(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<APIException> handleEntityExistException(EntityExistsException e) {
        APIException exception = new APIException(e.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<APIException> handleInvalidDateException(InvalidDateException e) {
        APIException exception = new APIException(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<APIException> handleNumberFormatException(NumberFormatException e) {
        APIException exception = new APIException(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}

