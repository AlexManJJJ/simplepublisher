package com.example.demo.service;

import com.example.demo.model.Publication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VkontakteServiceTest {

    @Autowired
    private TelegramService vkontakteService;

//    @Test
//    public void testFacebookPost() {
//        Publication publication = new Publication();
//        publication.setMessage("Message");
//
//        Publication result = vkontakteService.post(publication);
//        assertEquals(result, publication);
//    }

}