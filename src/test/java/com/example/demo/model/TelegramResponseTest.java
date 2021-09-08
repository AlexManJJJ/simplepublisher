package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TelegramResponseTest {

    private final int EXPECTED_ID_INT = 1337;

    @Test
    public void testMessagePost(){

        TelegramResponse telegramResponse = new TelegramResponse();
        telegramResponse.setOk(true);
        TelegramResponse.Result result = new TelegramResponse.Result();
        result.setMessageId(EXPECTED_ID_INT);
        telegramResponse.setResult(result);

        assertEquals(result, telegramResponse.getResult());
        assertTrue(telegramResponse.isOk());
    }
}
