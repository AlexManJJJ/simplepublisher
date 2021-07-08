package com.example.demo.service;

import com.example.demo.exception.InvalidPublicationException;
import com.example.demo.model.Publication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FacebookServiceTest {

    @Autowired
    private FacebookService facebookService;

    @Test
    public void testFacebookNullsNotAllowed() {
        assertThrows(InvalidPublicationException.class, () -> facebookService.post(null));
    }

    @Test
    public void testFacebookEmptyBody() {
        Publication publication = new Publication();
        publication.setHeader("Header");
        assertThrows(InvalidPublicationException.class, () -> facebookService.post(publication));
    }

    @Test
    public void testFacebookEmptyHeader() {
        Publication publication = new Publication();
        publication.setBody("Body");
        assertThrows(InvalidPublicationException.class, () -> facebookService.post(publication));
    }

    @Test
    public void testFacebookPost() {
        Publication publication = new Publication();
        publication.setHeader("Header");
        publication.setBody("Body");

        Publication result = facebookService.post(publication);
        assertEquals(result, publication);
    }

}