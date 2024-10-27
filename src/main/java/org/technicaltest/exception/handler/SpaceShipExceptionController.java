package org.technicaltest.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.technicaltest.exception.SpaceShipNotFoundException;

@ControllerAdvice
public class SpaceShipExceptionController {

    @ExceptionHandler(SpaceShipNotFoundException.class)
    public ResponseEntity<String> handleSpaceShipNotFoundException(SpaceShipNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
