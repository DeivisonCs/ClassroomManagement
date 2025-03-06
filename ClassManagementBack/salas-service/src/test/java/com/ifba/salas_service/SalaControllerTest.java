package com.ifba.salas_service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifba.salas_service.controllers.SalaController;
import com.ifba.salas_service.dtos.request.SalaRequestDTO;
import com.ifba.salas_service.dtos.response.SalaResponseDTO;
import com.ifba.salas_service.services.SalaService;






@WebMvcTest(SalaController.class)
public class SalaControllerTest {

    @MockBean
    private SalaService salaService;

    @InjectMocks
    private SalaController salaController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(salaController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testCriarSala() throws Exception {
        SalaRequestDTO requestDTO = new SalaRequestDTO();
        SalaResponseDTO responseDTO = new SalaResponseDTO();

        when(salaService.criarSala(any(SalaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/salas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizarSala() throws Exception {
        SalaRequestDTO requestDTO = new SalaRequestDTO();
        SalaResponseDTO responseDTO = new SalaResponseDTO();

        when(salaService.atualizarSala(anyLong(), any(SalaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/salas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testBuscarPorId() throws Exception {
        SalaResponseDTO responseDTO = new SalaResponseDTO();

        when(salaService.buscarPorId(anyLong())).thenReturn(responseDTO);

        mockMvc.perform(get("/api/salas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarTodas() throws Exception {
        when(salaService.listarTodas()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/salas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletarSala() throws Exception {
        mockMvc.perform(delete("/api/salas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}