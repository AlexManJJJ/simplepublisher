package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest {

    private final String MESSAGE = "message";
    private final String MESSAGE_2 = "message2";

    @Test
    public void testMessagePost(){

        Response response = new Response(MESSAGE);
        response.setMessage(MESSAGE_2);
        assertEquals(MESSAGE_2, response.getMessage());
    }

}
