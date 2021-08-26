package com.example.demo.service.client;

import com.example.demo.model.TelegramResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class TelegramClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramClient.class);

    private final String POST_URL;
    private final RestTemplate restTemplate;

    public TelegramClient(String POST_URL, RestTemplate restTemplate) {
        this.POST_URL = POST_URL;
        this.restTemplate = restTemplate;
    }

    public int post(String message) {
        TelegramResponse response =
                restTemplate.postForEntity(POST_URL + message, null, TelegramResponse.class).getBody();
        LOGGER.info("Received response from telegram: {}", response);
        return response.getResult().getMessageId();
    }
}
