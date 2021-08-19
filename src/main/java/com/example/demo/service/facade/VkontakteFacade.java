package com.example.demo.service.facade;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VkontakteFacade {
    private final VkApiClient vkApiClient;
    private final UserActor vkUser;
    private final Integer groupId;

    public VkontakteFacade(VkApiClient vkApiClient, UserActor vkUser, @Value("${vk.groupId}") Integer groupId) {
        this.vkApiClient = vkApiClient;
        this.vkUser = vkUser;
        this.groupId = groupId;
    }

    public Integer post(String message) throws Exception {
        return vkApiClient.wall()
                .post(vkUser)
                .ownerId(groupId)
                .message(message)
                .execute()
                .getPostId();
    }
}
