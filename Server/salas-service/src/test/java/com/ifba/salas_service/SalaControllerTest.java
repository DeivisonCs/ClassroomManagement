package com.ifba.salas_service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import com.ifba.salas_service.controllers.SalaController;
import com.ifba.salas_service.dtos.SalaDTO;
import com.ifba.salas_service.services.SalaService;

public class SalaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SalaService salaService;

    @InjectMocks
    private SalaController salaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(salaController).build();
    }

    @Test
    public void testListarSalas() throws Exception {
        List<SalaDTO> salas = Arrays.asList(new SalaDTO(1L, "Sala 101"), new SalaDTO(2L, "Sala 102"));
        when(salaService.listarSalas()).thenReturn(salas);

        mockMvc.perform(get("/api/salas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Sala 101"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Sala 102"));
    }

    @Test
    public void testBuscarPorId() throws Exception {
        SalaDTO salaDTO = new SalaDTO(1L, "Sala 101");
        when(salaService.buscarPorId(1L)).thenReturn(salaDTO);

        mockMvc.perform(get("/api/salas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Sala 101"));
    }

    @Test
    public void testSalvarSala() throws Exception {
        SalaDTO salaDTO = new SalaDTO(1L, "Sala 101");
        when(salaService.salvarSala(any(SalaDTO.class))).thenReturn(salaDTO);

        mockMvc.perform(post("/api/salas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(salaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Sala 101"));
    }

    @Test
    public void testDeletarSala() throws Exception {
        doNothing().when(salaService).deletarSala(1L);

        mockMvc.perform(delete("/api/salas/1"))
                .andExpect(status().isNoContent());
    }
}