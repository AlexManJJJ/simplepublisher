package com.example.demo.service;

import com.example.demo.model.Publication;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacebookService.class);

    private String facebookToken;
    private FacebookClient facebookClient;

    @Autowired
    public FacebookService(@Value("${facebook.token}") final String facebookToken) {
        this.facebookToken = facebookToken;
        this.facebookClient = new DefaultFacebookClient(facebookToken, Version.VERSION_11_0);
    }

    public Publication post(Publication publication) {
        LOGGER.info("Facebook: {}", publication);
        publishMessage(publication.getHeader()+" "+publication.getBody());
        return publication;
    }

    private String publishMessage(String message) {
        LOGGER.info("Message publishing: {}", message);

        FacebookType publishMessageResponse =
                facebookClient.publish("me/feed", FacebookType.class, Parameter.with("message", message));

        LOGGER.info("Published message ID: " + publishMessageResponse.getId());
        return publishMessageResponse.getId();
    }
}
