package com.example.demo.service;

import com.example.demo.exception.InvalidPublicationException;
import com.example.demo.model.Publication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class VkontakteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VkontakteService.class);

    public Publication post(Publication publication){
        validatePublication(publication);
        LOGGER.info("Vkontakte: {}", publication);
        return publication;
    }

    private void validatePublication(Publication publication){
        if (publication == null) {
            LOGGER.error("Publication can't be null!");
            throw new InvalidPublicationException("Publication can't be null!");
        }

        if (StringUtils.isEmpty(publication.getHeader())) {
            LOGGER.error("Header could not be empty! {}", publication);
            throw new InvalidPublicationException("Header could not be empty!");
        }

        if (StringUtils.isEmpty(publication.getBody())) {
            LOGGER.error("Body could not be empty! {}", publication);
            throw new InvalidPublicationException("Body could not be empty!");
        }
    }
}