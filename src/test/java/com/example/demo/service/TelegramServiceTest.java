package com.example.demo.service;

import com.example.demo.model.Publication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TelegramServiceTest {

    @Autowired
    private TelegramService telegramService;

    @Test
    public void testFacebookPost() {
        Publication publication = new Publication();
        publication.setMessage("Message");

        Publication result = telegramService.post(publication);
        assertEquals(result, publication);
    }

}