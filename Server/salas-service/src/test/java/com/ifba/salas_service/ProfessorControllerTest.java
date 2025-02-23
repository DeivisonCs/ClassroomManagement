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
import com.ifba.salas_service.controllers.ProfessorController;
import com.ifba.salas_service.dtos.request.ProfessorRequestDTO;
import com.ifba.salas_service.dtos.response.ProfessorResponseDTO;
import com.ifba.salas_service.services.ProfessorService;




@WebMvcTest(ProfessorController.class)
public class ProfessorControllerTest {

    @MockBean
    private ProfessorService professorService;

    @InjectMocks
    private ProfessorController professorController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(professorController).build();
    }

    @Test
    public void testCriarProfessor() throws Exception {
        ProfessorRequestDTO requestDTO = new ProfessorRequestDTO();
        ProfessorResponseDTO responseDTO = new ProfessorResponseDTO();

        when(professorService.criarProfessor(any(ProfessorRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/professores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizarProfessor() throws Exception {
        Long id = 1L;
        ProfessorRequestDTO requestDTO = new ProfessorRequestDTO();
        ProfessorResponseDTO responseDTO = new ProfessorResponseDTO();

        when(professorService.atualizarProfessor(eq(id), any(ProfessorRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/professores/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testBuscarPorId() throws Exception {
        Long id = 1L;
        ProfessorResponseDTO responseDTO = new ProfessorResponseDTO();

        when(professorService.buscarPorId(id)).thenReturn(responseDTO);

        mockMvc.perform(get("/api/professores/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarTodos() throws Exception {
        when(professorService.listarTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/professores"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletarProfessor() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/professores/{id}", id))
                .andExpect(status().isNoContent());
    }
}