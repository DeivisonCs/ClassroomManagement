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
import com.ifba.salas_service.controllers.AlunoController;
import com.ifba.salas_service.dtos.request.AlunoRequestDTO;
import com.ifba.salas_service.dtos.response.AlunoResponseDTO;
import com.ifba.salas_service.services.AlunoService;


@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    @MockBean
    private AlunoService alunoService;

    @InjectMocks
    private AlunoController alunoController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(alunoController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testCriarAluno() throws Exception {
        AlunoRequestDTO requestDTO = new AlunoRequestDTO();
        AlunoResponseDTO responseDTO = new AlunoResponseDTO();

        when(alunoService.criarAluno(any(AlunoRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizarAluno() throws Exception {
        AlunoRequestDTO requestDTO = new AlunoRequestDTO();
        AlunoResponseDTO responseDTO = new AlunoResponseDTO();

        when(alunoService.atualizarAluno(anyLong(), any(AlunoRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/alunos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testBuscarPorId() throws Exception {
        AlunoResponseDTO responseDTO = new AlunoResponseDTO();

        when(alunoService.buscarPorId(anyLong())).thenReturn(responseDTO);

        mockMvc.perform(get("/api/alunos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarTodos() throws Exception {
        when(alunoService.listarTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletarAluno() throws Exception {
        mockMvc.perform(delete("/api/alunos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}