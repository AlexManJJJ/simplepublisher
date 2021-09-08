package com.example.demo.controller;

import com.example.demo.exception.InvalidPublicationException;
import com.example.demo.model.Destination;
import com.example.demo.model.Publication;
import com.example.demo.service.FacebookService;
import com.example.demo.service.TelegramService;
import com.example.demo.service.VkontakteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static com.example.demo.model.Destination.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


public class PublicationControllerTest {

    private PublicationController controller;

    @Mock
    private VkontakteService vkontakteService;

    @Mock
    private FacebookService facebookService;

    @Mock
    private TelegramService telegramService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new PublicationController(vkontakteService, facebookService, telegramService);
    }

    @Test
    public void testSendPublication() throws Exception {
        Publication publication = new Publication();
        publication.setMessage("Message");

        Publication result = controller.create(publication);
        assertEquals(result, publication);

        verify(vkontakteService).post(publication);
        verify(facebookService).post(publication);
        verify(telegramService).post(publication);
    }

    @Test
    public void testSendPublicationOnlyToVkontakteAndTelegram() throws Exception {
        Publication publication = new Publication();
        publication.setMessage("Message");
        Set<Destination> destinations = new HashSet<>();
        destinations.add(VKONTAKTE);
        destinations.add(TELEGRAM);
        publication.setDestinations(destinations);

        Publication result = controller.create(publication);
        assertEquals(result, publication);

        verify(vkontakteService).post(publication);
        verify(telegramService).post(publication);
        verify(facebookService, never()).post(publication);
    }

//    @Test
//    public void testSendPublicationWithNullDestination() throws Exception {
//        Publication publication = new Publication();
//        publication.setMessage("Message");
//        Set<Destination> destinations = new HashSet<>();
//        destinations.add(null);
//        publication.setDestinations(destinations);
//
//
//        assertThrows(NullPointerException.class, () -> controller.create(publication));
//    }

    @Test
    public void testSendPublicationWithoutMessage() throws Exception {
        Publication publication = new Publication();
        publication.setMessage(null);
        assertThrows(InvalidPublicationException.class, () -> controller.create(publication));
        verify(vkontakteService, never()).post(publication);
        verify(facebookService, never()).post(publication);
        verify(telegramService, never()).post(publication);
    }

    @Test
    public void testSendPublicationWithNullPublication() throws Exception {
        Publication publication = null;
        assertThrows(InvalidPublicationException.class, () -> controller.create(publication));
        verify(vkontakteService, never()).post(publication);
        verify(facebookService, never()).post(publication);
        verify(telegramService, never()).post(publication);
    }
}
