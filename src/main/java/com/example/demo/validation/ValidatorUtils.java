package com.example.demo.validation;

import com.example.demo.exception.InvalidPublicationException;
import com.example.demo.model.Publication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class ValidatorUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorUtils.class);

    public static void validatePublication(Publication publication){
        if (publication == null) {
            LOGGER.error("Publication can't be null!");
            throw new InvalidPublicationException("Publication can't be null!");
        }

        if (!StringUtils.hasLength(publication.getMessage())) {
            LOGGER.error("Header could not be empty! {}", publication);
            throw new InvalidPublicationException("Header could not be empty!");
        }
    }
}
