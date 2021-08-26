package com.example.demo.controller;

import com.example.demo.model.Destination;
import com.example.demo.model.Publication;
import com.example.demo.service.BaseService;
import com.example.demo.service.FacebookService;
import com.example.demo.service.TelegramService;
import com.example.demo.service.VkontakteService;
import com.example.demo.validation.ValidatorUtils;
import com.vk.api.sdk.exceptions.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumMap;
import java.util.Map;

import static com.example.demo.model.Destination.*;

@RestController
public class PublicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicationController.class);

    private final Map<Destination, BaseService> services;

    @Autowired
    private VkontakteService vkontakteService;

    @Autowired
    private TelegramService telegramService;

    @Autowired
    private FacebookService facebookService;

    public PublicationController(VkontakteService vkontakteService,
                                 FacebookService facebookService,
                                 TelegramService telegramService) {
        this.services = new EnumMap<>(Destination.class);
        this.services.put(VKONTAKTE, vkontakteService);
        this.services.put(FACEBOOK, facebookService);
        this.services.put(TELEGRAM, telegramService);
    }

    @PostMapping("/publications")
    public Publication create(@RequestBody Publication publication) {
        LOGGER.info("Received in PublicationController: {}", publication);

        ValidatorUtils.validatePublication(publication);

        try {
            vkontakteService.post(publication);
            telegramService.post(publication);
//            facebookService.post(publication);
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return publication;
    }

    private void postToAllPlatforms(Publication publication) {
        LOGGER.info("Sending {} to all platforms", publication);
        services.values().forEach(service -> {
            try {
                service.post(publication);
            } catch (Exception e) {
                LOGGER.error("Can`t send publication {}", publication, e);
            }
        });
    }

    private void postForFilteredPlatforms(Publication publication) {
        LOGGER.info("Sending {} to all platforms", publication, publication.getDestinations());
        publication.getDestinations().forEach(destination -> {
            try {
                LOGGER.info("Sending to {}: {}", destination, publication);
                services.get(destination).post(publication);
            } catch (Exception e) {
                LOGGER.error("Can't send publication {} to {}", publication, destination, e);
            }
        });
    }
}
