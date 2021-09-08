package com.example.demo.service;

import com.example.demo.model.Publication;
import com.example.demo.service.client.TelegramClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TelegramServiceTest {

    @Mock
    private TelegramClient telegramClient;

    @InjectMocks
    private TelegramService telegramService;

    private final String PUBLICATION_MESSAGE = "Message";
    private final String EXPECTED_ID = "1337";

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(telegramClient.post(PUBLICATION_MESSAGE)).thenReturn(EXPECTED_ID);
    }

    @Test
    public void testVkontaktePost() throws Exception {
        Publication publication = new Publication();
        publication.setMessage("Message");

        String publicationId = telegramService.post(publication);
        assertEquals(EXPECTED_ID, publicationId);

        verify(telegramClient).post(PUBLICATION_MESSAGE);
    }
}