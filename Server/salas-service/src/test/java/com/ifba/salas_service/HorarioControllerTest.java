package com.ifba.salas_service;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifba.salas_service.controllers.HorarioController;
import com.ifba.salas_service.dtos.request.HorarioRequestDTO;
import com.ifba.salas_service.dtos.response.HorarioResponseDTO;
import com.ifba.salas_service.services.HorarioService;





@ExtendWith(MockitoExtension.class)
public class HorarioControllerTest {

    @InjectMocks
    private HorarioController horarioController;

    @Mock
    private HorarioService horarioService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(horarioController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void criarHorario_ShouldReturnCreatedAndHorarioResponseDTO() throws Exception {
        HorarioRequestDTO requestDTO = new HorarioRequestDTO();
        HorarioResponseDTO responseDTO = new HorarioResponseDTO();

        when(horarioService.criarHorario(any(HorarioRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/horarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void atualizarHorario_ShouldReturnOkAndHorarioResponseDTO() throws Exception {
        Long id = 1L;
        HorarioRequestDTO requestDTO = new HorarioRequestDTO();
        HorarioResponseDTO responseDTO = new HorarioResponseDTO();

        when(horarioService.atualizarHorario(eq(id), any(HorarioRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/horarios/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void buscarPorId_ShouldReturnOkAndHorarioResponseDTO() throws Exception {
        Long id = 1L;
        HorarioResponseDTO responseDTO = new HorarioResponseDTO();

        when(horarioService.buscarPorId(id)).thenReturn(responseDTO);

        mockMvc.perform(get("/api/horarios/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void listarTodos_ShouldReturnOkAndListOfHorarioResponseDTO() throws Exception {
        List<HorarioResponseDTO> lista = Arrays.asList(new HorarioResponseDTO(), new HorarioResponseDTO());

        when(horarioService.listarTodos()).thenReturn(lista);

        mockMvc.perform(get("/api/horarios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(lista)));
    }

    @Test
    void deletarHorario_ShouldReturnNoContent() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/horarios/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(horarioService, times(1)).deletarHorario(id);
    }
}