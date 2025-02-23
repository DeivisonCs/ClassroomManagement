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
import com.ifba.salas_service.controllers.TurmaController;
import com.ifba.salas_service.dtos.request.TurmaRequestDTO;
import com.ifba.salas_service.dtos.response.TurmaResponseDTO;
import com.ifba.salas_service.services.TurmaService;






@WebMvcTest(TurmaController.class)
public class TurmaControllerTest {

    @MockBean
    private TurmaService turmaService;

    @InjectMocks
    private TurmaController turmaController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(turmaController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testCriarTurma() throws Exception {
        TurmaRequestDTO requestDTO = new TurmaRequestDTO();
        TurmaResponseDTO responseDTO = new TurmaResponseDTO();

        when(turmaService.criarTurma(any(TurmaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/turmas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizarTurma() throws Exception {
        TurmaRequestDTO requestDTO = new TurmaRequestDTO();
        TurmaResponseDTO responseDTO = new TurmaResponseDTO();

        when(turmaService.atualizarTurma(anyLong(), any(TurmaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/turmas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testBuscarPorId() throws Exception {
        TurmaResponseDTO responseDTO = new TurmaResponseDTO();

        when(turmaService.buscarPorId(anyLong())).thenReturn(responseDTO);

        mockMvc.perform(get("/api/turmas/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarTodas() throws Exception {
        when(turmaService.listarTodas()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/turmas"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletarTurma() throws Exception {
        mockMvc.perform(delete("/api/turmas/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}