package com.example.demo.service;

import com.example.demo.model.Publication;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.GraphResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacebookService.class);

    private FacebookClient facebookClient;

    @Autowired
    public FacebookService(FacebookClient facebookClient) {
        this.facebookClient = facebookClient;
    }

    public String post(Publication publication) {
        LOGGER.info("Facebook: {}", publication);
        return publishMessage(publication.getMessage());
    }

    private String publishMessage(String message) {
        LOGGER.info("Message publishing: {}", message);

        GraphResponse publishMessageResponse =
                facebookClient.publish("me/feed", GraphResponse.class, Parameter.with("message", message));

        LOGGER.info("Published message ID: " + publishMessageResponse.getId());
        return publishMessageResponse.getId();
    }
}
