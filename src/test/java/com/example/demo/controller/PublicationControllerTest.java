package com.example.demo.controller;

import com.example.demo.exception.InvalidPublicationException;
import com.example.demo.model.Publication;
import com.example.demo.service.FacebookService;
import com.example.demo.service.TelegramService;
import com.example.demo.service.VkontakteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void testSendPublication() {
        Publication publication = new Publication();
        publication.setBody("Body");
        publication.setHeader("Header");

        Publication result = controller.create(publication);
        assertEquals(result, publication);

        verify(vkontakteService).post(publication);
        verify(facebookService).post(publication);
        verify(telegramService).post(publication);
    }

    @Test
    public void testSendPublicationWithoutHeader() {
        Publication publication = new Publication();
        publication.setBody("Body");
        assertThrows(InvalidPublicationException.class, () -> controller.create(publication));
        verify(vkontakteService, never()).post(publication);
        verify(facebookService, never()).post(publication);
        verify(telegramService, never()).post(publication);
    }

    @Test
    public void testSendPublicationWithoutBody() {
        Publication publication = new Publication();
        publication.setHeader("Header");
        assertThrows(InvalidPublicationException.class, () -> controller.create(publication));
        verify(vkontakteService, never()).post(publication);
        verify(facebookService, never()).post(publication);
        verify(telegramService, never()).post(publication);
    }

    @Test
    public void testSendPublicationWithNullPublication() {
        Publication publication = null;
        assertThrows(InvalidPublicationException.class, () -> controller.create(publication));
        verify(vkontakteService, never()).post(publication);
        verify(facebookService, never()).post(publication);
        verify(telegramService, never()).post(publication);
    }
}
