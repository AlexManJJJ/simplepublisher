package com.example.demo.service;

import com.example.demo.model.Publication;
import com.example.demo.service.facade.VkontakteFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class VkontakteServiceTest {

    @Mock
    private VkontakteFacade vkontakteFacade;

    @InjectMocks
    private VkontakteService vkontakteService;

    private final String PUBLICATION_MESSAGE = "Message";
    private final String EXPECTED_ID = "1337";

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(vkontakteFacade.post(PUBLICATION_MESSAGE)).thenReturn(EXPECTED_ID);
    }

    @Test
    public void testVkontaktePost() throws Exception {
        Publication publication = new Publication();
        publication.setMessage("Message");

        String publicationId = vkontakteService.post(publication);
        assertEquals(EXPECTED_ID, publicationId);

        verify(vkontakteFacade).post(PUBLICATION_MESSAGE);
    }
}