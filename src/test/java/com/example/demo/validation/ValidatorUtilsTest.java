package com.example.demo.validation;

import com.example.demo.exception.InvalidPublicationException;
import com.example.demo.model.Publication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorUtilsTest {

    @Test
    public void testEmptyHeader() {
        Publication publication = new Publication();
        publication.setBody("Body");
        assertThrows(InvalidPublicationException.class, () -> ValidatorUtils.validatePublication(publication));
    }

    @Test
    public void testEmptyBody() {
        Publication publication = new Publication();
        publication.setHeader("Header");
        assertThrows(InvalidPublicationException.class, () -> ValidatorUtils.validatePublication(publication));
    }

    @Test
    public void testEmptyBodyAndHeader() {
        Publication publication = new Publication();
        assertThrows(InvalidPublicationException.class, () -> ValidatorUtils.validatePublication(publication));
    }

    @Test
    public void testFilledPublication() {
        Publication publication = new Publication();
        publication.setHeader("Header");
        publication.setBody("Body");
        assertDoesNotThrow(() -> ValidatorUtils.validatePublication(publication));
    }

    @Test
    public void testNullPublication() {
        assertThrows(InvalidPublicationException.class,() -> ValidatorUtils.validatePublication(null));
    }
}
