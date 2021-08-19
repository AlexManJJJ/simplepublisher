package com.example.demo.config;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SocialConfig {
    private static final String FACEBOOK_PAGE_CONFIG = "facebook.token";
    private static final String VK_USER_ID = "vk.userId";
    private static final String VK_USER_TOKEN = "vk.userToken";

    @Bean
    public FacebookClient getFacebookClient(Environment environment) {
        return new DefaultFacebookClient(environment.getProperty(FACEBOOK_PAGE_CONFIG), Version.VERSION_11_0);
    }

    @Bean
    public VkApiClient getVkApiClient() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        return new VkApiClient(transportClient);
    }

    @Bean
    public UserActor getUserActor(Environment environment) {
        return new UserActor(Integer.parseInt(environment.getProperty(VK_USER_ID)),
                environment.getProperty(VK_USER_TOKEN));
    }
}
