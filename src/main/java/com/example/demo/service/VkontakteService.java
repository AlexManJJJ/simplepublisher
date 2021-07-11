package com.example.demo.service;

import com.example.demo.model.Publication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class VkontakteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VkontakteService.class);

    public Publication post(Publication publication){
        LOGGER.info("Vkontakte: {}", publication);
        return publication;
    }
}
