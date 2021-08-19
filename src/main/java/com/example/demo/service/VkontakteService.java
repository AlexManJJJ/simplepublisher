package com.example.demo.service;

import com.example.demo.model.Publication;
import com.example.demo.service.facade.VkontakteFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VkontakteService implements BaseService{

    private static final Logger LOGGER = LoggerFactory.getLogger(VkontakteService.class);

    private VkontakteFacade vkontakteFacade;

    public VkontakteService(VkontakteFacade vkontakteFacade) {
        this.vkontakteFacade = vkontakteFacade;
    }

    public String post(Publication publication) throws Exception {
        LOGGER.info("Vkontakte: {}", publication);
        String postId = vkontakteFacade.post(publication.getMessage()).toString();
        LOGGER.info("Post Id: {}", postId);

        return postId;
    }
}
