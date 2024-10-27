package org.technicaltest.exception;

public class SpaceShipNotFoundException extends RuntimeException {
    public SpaceShipNotFoundException(String message) {
        super(message);
    }
}
