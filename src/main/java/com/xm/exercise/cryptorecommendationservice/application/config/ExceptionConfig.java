package com.xm.exercise.cryptorecommendationservice.application.config;

import com.xm.exercise.cryptorecommendationservice.application.exception.FileReadException;
import com.xm.exercise.cryptorecommendationservice.application.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileReadException.class)
    public ResponseEntity handleFileReadException(FileReadException ex) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
