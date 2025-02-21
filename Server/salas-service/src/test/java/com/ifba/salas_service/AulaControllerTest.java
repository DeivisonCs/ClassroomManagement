package com.ifba.salas_service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ifba.salas_service.controllers.AulaController;
import com.ifba.salas_service.dtos.AulaDTO;
import com.ifba.salas_service.services.AulaService;

public class AulaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AulaService aulaService;

    @InjectMocks
    private AulaController aulaController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(aulaController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testListarAulas() throws Exception {
        List<AulaDTO> aulas = Arrays.asList(
            new AulaDTO(1L, 1L, 1L, "Segunda-feira", LocalTime.of(8, 0), 50),
            new AulaDTO(2L, 2L, 2L, "Terça-feira", LocalTime.of(9, 0), 50)
        );
        when(aulaService.listarAulas()).thenReturn(aulas);

        mockMvc.perform(get("/api/aulas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].disciplinaId").value(1L))
                .andExpect(jsonPath("$[0].salaId").value(1L))
                .andExpect(jsonPath("$[0].diaSemana").value("Segunda-feira"))
                .andExpect(jsonPath("$[0].horarioInicio").value("08:00:00"))
                .andExpect(jsonPath("$[0].duracao").value(50))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].disciplinaId").value(2L))
                .andExpect(jsonPath("$[1].salaId").value(2L))
                .andExpect(jsonPath("$[1].diaSemana").value("Terça-feira"))
                .andExpect(jsonPath("$[1].horarioInicio").value("09:00:00"))
                .andExpect(jsonPath("$[1].duracao").value(50));
    }

    @Test
    public void testCriarAula() throws Exception {
        AulaDTO aulaDTO = new AulaDTO(1L, 1L, 1L, "Segunda-feira", LocalTime.of(8, 0), 50);
        when(aulaService.salvarAula(any(AulaDTO.class))).thenReturn(aulaDTO);

        mockMvc.perform(post("/api/aulas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aulaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.disciplinaId").value(1L))
                .andExpect(jsonPath("$.salaId").value(1L))
                .andExpect(jsonPath("$.diaSemana").value("Segunda-feira"))
                .andExpect(jsonPath("$.horarioInicio").value("08:00:00"))
                .andExpect(jsonPath("$.duracao").value(50));
    }

    @Test
    public void testExcluirAula() throws Exception {
        doNothing().when(aulaService).excluirAula(1L);

        mockMvc.perform(delete("/api/aulas/1"))
                .andExpect(status().isNoContent());
    }
}