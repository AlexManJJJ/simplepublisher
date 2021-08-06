package com.example.demo.service;

import com.example.demo.model.Publication;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.GraphResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FacebookServiceTest {

    @Mock
    private FacebookClient facebookClient;

    @InjectMocks
    private FacebookService facebookService;

    private final String PUBLICATION_MESSAGE = "Message";
    private final String EXPECTED_ID = "1337_7331";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        GraphResponse graphResponse = new GraphResponse();
        graphResponse.setSuccess(true);
        graphResponse.setId(EXPECTED_ID);
        when(facebookClient.publish("me/feed",
                GraphResponse.class,
                Parameter.with("message", PUBLICATION_MESSAGE)))
                .thenReturn(graphResponse);
    }

    @Test
    public void testFacebookPost() {
        Publication publication = new Publication();
        publication.setMessage(PUBLICATION_MESSAGE);

        String postId = facebookService.post(publication);
        assertEquals(postId, EXPECTED_ID);

        verify(facebookClient).publish(eq("me/feed"),
                eq(GraphResponse.class),
                eq(Parameter.with("message", PUBLICATION_MESSAGE)));
    }


}