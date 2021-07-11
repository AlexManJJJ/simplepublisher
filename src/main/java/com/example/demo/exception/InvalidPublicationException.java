package com.example.demo.exception;

public class InvalidPublicationException extends RuntimeException {

    public InvalidPublicationException(String message) {
        super(message);
    }

}