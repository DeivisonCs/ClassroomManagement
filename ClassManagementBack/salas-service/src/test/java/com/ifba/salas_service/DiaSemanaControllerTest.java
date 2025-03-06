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
import com.ifba.salas_service.controllers.DiaSemanaController;
import com.ifba.salas_service.dtos.request.DiaSemanaRequestDTO;
import com.ifba.salas_service.dtos.response.DiaSemanaResponseDTO;
import com.ifba.salas_service.services.DiaSemanaService;






@WebMvcTest(DiaSemanaController.class)
public class DiaSemanaControllerTest {

    @MockBean
    private DiaSemanaService diaSemanaService;

    @InjectMocks
    private DiaSemanaController diaSemanaController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(diaSemanaController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testCriarDiaSemana() throws Exception {
        DiaSemanaRequestDTO requestDTO = new DiaSemanaRequestDTO();
        DiaSemanaResponseDTO responseDTO = new DiaSemanaResponseDTO();

        when(diaSemanaService.criarDiaSemana(any(DiaSemanaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/dias-semana")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizarDiaSemana() throws Exception {
        DiaSemanaRequestDTO requestDTO = new DiaSemanaRequestDTO();
        DiaSemanaResponseDTO responseDTO = new DiaSemanaResponseDTO();

        when(diaSemanaService.atualizarDiaSemana(anyLong(), any(DiaSemanaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/dias-semana/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testBuscarPorId() throws Exception {
        DiaSemanaResponseDTO responseDTO = new DiaSemanaResponseDTO();

        when(diaSemanaService.buscarPorId(anyLong())).thenReturn(responseDTO);

        mockMvc.perform(get("/api/dias-semana/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarTodos() throws Exception {
        when(diaSemanaService.listarTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/dias-semana")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletarDiaSemana() throws Exception {
        mockMvc.perform(delete("/api/dias-semana/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}