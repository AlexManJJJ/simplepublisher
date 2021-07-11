package com.example.demo.service;

import com.example.demo.model.Publication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TelegramService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramService.class);

    public Publication post(Publication publication){
        LOGGER.info("Telegram: {}", publication);
        return publication;
    }
}
