package com.example.demo.service.client;

import com.example.demo.model.TelegramResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TelegramClientTest {

    private final String PUBLICATION_MESSAGE = "Message";
    private final String EXPECTED_ID = "1337";
    private final int EXPECTED_ID_INT = 1337;
    private final String POST_URL = "google.ru/";

    @Mock
    RestTemplate restTemplate;
    TelegramClient telegramClient;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        telegramClient = new TelegramClient(POST_URL, restTemplate);
        TelegramResponse telegramResponse = new TelegramResponse();
        TelegramResponse.Result result = new TelegramResponse.Result();
        result.setMessageId(EXPECTED_ID_INT);
        telegramResponse.setResult(result);
        when(restTemplate.postForEntity(anyString(), any(), any())).thenReturn(new ResponseEntity<>(telegramResponse, HttpStatus.OK));
    }

    @Test
    public void testMessagePost(){

        String publicationId = telegramClient.post(PUBLICATION_MESSAGE);
        assertEquals(EXPECTED_ID, publicationId);

        verify(restTemplate, times(1)).postForEntity(POST_URL+PUBLICATION_MESSAGE, null, TelegramResponse.class);
    }
}
