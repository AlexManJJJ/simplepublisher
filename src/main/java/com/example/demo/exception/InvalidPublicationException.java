package com.example.demo.exception;

public class InvalidPublicationException extends RuntimeException {

    public InvalidPublicationException() {
    }

    public InvalidPublicationException(String message) {
        super(message);
    }

}