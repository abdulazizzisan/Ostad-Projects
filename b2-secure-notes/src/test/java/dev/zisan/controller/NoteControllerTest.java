package dev.zisan.controller;

import dev.zisan.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    NoteRepository noteRepository;

    @Test
    void pingEndpointShouldReturnStatusOk() throws Exception {
        mockMvc.perform(get("/api/notes/ping"))
                .andExpect(status().isOk())
                .andExpect(content().string("pong"));
    }
}