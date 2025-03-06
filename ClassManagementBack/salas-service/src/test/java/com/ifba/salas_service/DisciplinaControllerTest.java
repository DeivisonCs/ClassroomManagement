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
import com.ifba.salas_service.controllers.DisciplinaController;
import com.ifba.salas_service.dtos.request.DisciplinaRequestDTO;
import com.ifba.salas_service.dtos.response.DisciplinaResponseDTO;
import com.ifba.salas_service.services.DisciplinaService;






@WebMvcTest(DisciplinaController.class)
public class DisciplinaControllerTest {

    @MockBean
    private DisciplinaService disciplinaService;

    @InjectMocks
    private DisciplinaController disciplinaController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(disciplinaController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testCriarDisciplina() throws Exception {
        DisciplinaRequestDTO requestDTO = new DisciplinaRequestDTO();
        DisciplinaResponseDTO responseDTO = new DisciplinaResponseDTO();

        when(disciplinaService.criarDisciplina(any(DisciplinaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/disciplinas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizarDisciplina() throws Exception {
        DisciplinaRequestDTO requestDTO = new DisciplinaRequestDTO();
        DisciplinaResponseDTO responseDTO = new DisciplinaResponseDTO();

        when(disciplinaService.atualizarDisciplina(anyLong(), any(DisciplinaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/disciplinas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testBuscarPorId() throws Exception {
        DisciplinaResponseDTO responseDTO = new DisciplinaResponseDTO();

        when(disciplinaService.buscarPorId(anyLong())).thenReturn(responseDTO);

        mockMvc.perform(get("/api/disciplinas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarTodas() throws Exception {
        when(disciplinaService.listarTodas()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/disciplinas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletarDisciplina() throws Exception {
        mockMvc.perform(delete("/api/disciplinas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}