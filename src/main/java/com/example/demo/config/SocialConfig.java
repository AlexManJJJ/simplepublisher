package com.example.demo.config;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SocialConfig {
    private static final String FACEBOOK_PAGE_CONFIG = "facebook.token";

    @Bean
    public FacebookClient getFacebookClient(Environment environment) {
        return new DefaultFacebookClient(environment.getProperty(FACEBOOK_PAGE_CONFIG), Version.VERSION_11_0);
    }
}
