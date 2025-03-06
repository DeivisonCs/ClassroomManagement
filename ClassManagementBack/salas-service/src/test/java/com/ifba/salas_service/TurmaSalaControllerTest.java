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
import com.ifba.salas_service.controllers.TurmaSalaController;
import com.ifba.salas_service.dtos.request.TurmaSalaRequestDTO;
import com.ifba.salas_service.dtos.response.TurmaSalaResponseDTO;
import com.ifba.salas_service.services.TurmaSalaService;




@WebMvcTest(TurmaSalaController.class)
public class TurmaSalaControllerTest {

    @MockBean
    private TurmaSalaService turmaSalaService;

    @InjectMocks
    private TurmaSalaController turmaSalaController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(turmaSalaController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testCriar() throws Exception {
        TurmaSalaRequestDTO requestDTO = new TurmaSalaRequestDTO();
        TurmaSalaResponseDTO responseDTO = new TurmaSalaResponseDTO();

        when(turmaSalaService.criarTurmaSala(any(TurmaSalaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/turmas-salas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizar() throws Exception {
        TurmaSalaRequestDTO requestDTO = new TurmaSalaRequestDTO();
        TurmaSalaResponseDTO responseDTO = new TurmaSalaResponseDTO();

        when(turmaSalaService.atualizarTurmaSala(anyLong(), any(TurmaSalaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/turmas-salas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testBuscarPorId() throws Exception {
        TurmaSalaResponseDTO responseDTO = new TurmaSalaResponseDTO();

        when(turmaSalaService.buscarPorId(anyLong())).thenReturn(responseDTO);

        mockMvc.perform(get("/api/turmas-salas/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarTodos() throws Exception {
        when(turmaSalaService.listarTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/turmas-salas"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletar() throws Exception {
        mockMvc.perform(delete("/api/turmas-salas/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}