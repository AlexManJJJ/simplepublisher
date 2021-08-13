package com.example.demo.validation;

import com.example.demo.exception.InvalidPublicationException;
import com.example.demo.model.Publication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorUtilsTest {

    @Test
    public void testEmptyMessage() {
        Publication publication = new Publication();
        publication.setMessage("");
        assertThrows(InvalidPublicationException.class, () -> ValidatorUtils.validatePublication(publication));
    }

    @Test
    public void testFilledPublication() {
        Publication publication = new Publication();
        publication.setMessage("Message");
        assertDoesNotThrow(() -> ValidatorUtils.validatePublication(publication));
    }

    @Test
    public void testNullPublication() {
        assertThrows(InvalidPublicationException.class,() -> ValidatorUtils.validatePublication(null));
    }
}
