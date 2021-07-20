package com.example.demo.controller;

import com.example.demo.model.Publication;
import com.example.demo.service.FacebookService;
import com.example.demo.service.TelegramService;
import com.example.demo.service.VkontakteService;
import com.example.demo.validation.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicationController.class);

    @Autowired
    private VkontakteService vkontakteService;

    @Autowired
    private TelegramService telegramService;

    @Autowired
    private FacebookService facebookService;

    public PublicationController(VkontakteService vkontakteService,
                                 FacebookService facebookService,
                                 TelegramService telegramService) {
        this.vkontakteService = vkontakteService;
        this.facebookService = facebookService;
        this.telegramService = telegramService;
    }

    @PostMapping("/publications")
    public Publication create(@RequestBody Publication publication) {
        LOGGER.info("Received in PublicationController: {}", publication);

        ValidatorUtils.validatePublication(publication);

        vkontakteService.post(publication);
        telegramService.post(publication);
        facebookService.post(publication);

        return publication;
    }
}
