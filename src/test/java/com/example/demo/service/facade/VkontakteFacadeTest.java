package com.example.demo.service.facade;

import com.example.demo.model.TelegramResponse;
import com.example.demo.service.client.TelegramClient;
import com.vk.api.sdk.actions.Wall;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.responses.PostResponse;
import com.vk.api.sdk.queries.wall.WallPostQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class VkontakteFacadeTest {

    private final String PUBLICATION_MESSAGE = "Message";
    private final String EXPECTED_ID = "1337";
    private final Integer EXPECTED_ID_INT = 1337;
    private final int GROUP_ID = 1333333337;

    @Mock
    TransportClient transportClient;
    @Mock
    VkApiClient vkApiClient;
    @Mock
    UserActor vkUser;
//    @Mock
//    WallPostQuery wallPostQuery;
    @Mock
    PostResponse postResponse;
    VkontakteFacade vkontakteFacade;

//    TransportClient transportClient = HttpTransportClient.getInstance();
//        return new VkApiClient(transportClient);
//
//    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        vkontakteFacade = new VkontakteFacade(vkApiClient, vkUser, GROUP_ID);
        when(vkApiClient.wall()).thenReturn(new Wall(vkApiClient));
        PostResponse postResponse = new PostResponse();
        postResponse.setPostId(EXPECTED_ID_INT);
        WallPostQuery wallPostQuery = new WallPostQuery(vkApiClient, vkUser);
//        wallPostQuery.execute()
//        when(wallPostQuery.execute()).thenReturn(postResponse);
//        when(vkontakteFacade.post(PUBLICATION_MESSAGE)).thenReturn(EXPECTED_ID);
    }

//    @Test
    public void testMessagePost() throws Exception {

        String publicationId = vkontakteFacade.post(PUBLICATION_MESSAGE);
        assertEquals(EXPECTED_ID, publicationId);

//        verify(vkontakteFacade, times(1)).post(POST_URL+PUBLICATION_MESSAGE, null, TelegramResponse.class);
    }
}
