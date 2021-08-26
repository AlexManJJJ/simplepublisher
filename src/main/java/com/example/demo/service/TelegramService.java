package com.example.demo.service;

import com.example.demo.model.Publication;
import com.example.demo.service.client.TelegramClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TelegramService implements BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramService.class);

    private final TelegramClient telegramClient;

    public TelegramService(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    public String post(Publication publication){
        LOGGER.info("Sending publication: {}", publication);

        int postId = telegramClient.post(publication.getMessage());

        return String.valueOf(postId);
    }
}
