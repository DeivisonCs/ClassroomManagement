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
import com.ifba.salas_service.controllers.AulaController;
import com.ifba.salas_service.dtos.request.AulaRequestDTO;
import com.ifba.salas_service.dtos.response.AulaResponseDTO;
import com.ifba.salas_service.services.AulaService;




@WebMvcTest(AulaController.class)
public class AulaControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private AulaService aulaService;

    @InjectMocks
    private AulaController aulaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(aulaController).build();
    }

    @Test
    public void testCriarAula() throws Exception {
        AulaRequestDTO requestDTO = new AulaRequestDTO();
        AulaResponseDTO responseDTO = new AulaResponseDTO();

        when(aulaService.criarAula(any(AulaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/aulas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizarAula() throws Exception {
        AulaRequestDTO requestDTO = new AulaRequestDTO();
        AulaResponseDTO responseDTO = new AulaResponseDTO();

        when(aulaService.atualizarAula(anyLong(), any(AulaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/aulas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testBuscarPorId() throws Exception {
        AulaResponseDTO responseDTO = new AulaResponseDTO();

        when(aulaService.buscarPorId(anyLong())).thenReturn(responseDTO);

        mockMvc.perform(get("/api/aulas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarTodas() throws Exception {
        when(aulaService.listarTodas()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/aulas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletarAula() throws Exception {
        mockMvc.perform(delete("/api/aulas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}