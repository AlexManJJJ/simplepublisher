package com.example.demo.exception;

import com.example.demo.model.Response;
import com.restfb.exception.FacebookOAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {
    @ExceptionHandler({InvalidPublicationException.class, FacebookOAuthException.class})
    public ResponseEntity<Response> handleException(InvalidPublicationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
