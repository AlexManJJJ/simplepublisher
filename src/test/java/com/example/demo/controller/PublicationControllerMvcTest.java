package com.example.demo.controller;

import com.example.demo.model.Publication;
import com.example.demo.service.FacebookService;
import com.example.demo.service.TelegramService;
import com.example.demo.service.VkontakteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublicationController.class)
public class PublicationControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VkontakteService vkontakteService;

    @MockBean
    private FacebookService facebookService;

    @MockBean
    private TelegramService telegramService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCreateSuccess() throws Exception {
        Publication publication = new Publication();
        publication.setMessage("Message");
        mockMvc.perform(post("/publications")
                .content(mapper.writeValueAsString(publication))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testMessageAbsence() throws Exception {
        Publication publication = new Publication();
        publication.setMessage(null);
        mockMvc.perform(post("/publications")
                .content(mapper.writeValueAsString(publication))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Header could not be empty!"));
    }

}
