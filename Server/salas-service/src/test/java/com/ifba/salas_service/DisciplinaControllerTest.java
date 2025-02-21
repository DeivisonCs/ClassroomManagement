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
import com.ifba.salas_service.controllers.DisciplinaController;
import com.ifba.salas_service.dtos.DisciplinaDTO;
import com.ifba.salas_service.services.DisciplinaService;

public class DisciplinaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DisciplinaService disciplinaService;

    @InjectMocks
    private DisciplinaController disciplinaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(disciplinaController).build();
    }

    @Test
    public void testListarDisciplinas() throws Exception {
        List<DisciplinaDTO> disciplinas = Arrays.asList(new DisciplinaDTO(1L, "Matemática", "MAT101", "Prof. João", 1L), new DisciplinaDTO(2L, "Português", "POR101", "Prof. Maria", 2L));
        when(disciplinaService.listarDisciplinas()).thenReturn(disciplinas);

        mockMvc.perform(get("/api/disciplinas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Matemática"))
                .andExpect(jsonPath("$[0].codigoTurma").value("MAT101"))
                .andExpect(jsonPath("$[0].nomeProfessor").value("Prof. João"))
                .andExpect(jsonPath("$[0].professorId").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Português"))
                .andExpect(jsonPath("$[1].codigoTurma").value("POR101"))
                .andExpect(jsonPath("$[1].nomeProfessor").value("Prof. Maria"))
                .andExpect(jsonPath("$[1].professorId").value(2L));
    }

    @Test
    public void testBuscarDisciplina() throws Exception {
        DisciplinaDTO disciplinaDTO = new DisciplinaDTO(1L, "Matemática", "MAT101", "Prof. João", 1L);
        when(disciplinaService.buscarPorId(1L)).thenReturn(disciplinaDTO);

        mockMvc.perform(get("/api/disciplinas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Matemática"))
                .andExpect(jsonPath("$.codigoTurma").value("MAT101"))
                .andExpect(jsonPath("$.nomeProfessor").value("Prof. João"))
                .andExpect(jsonPath("$.professorId").value(1L));
    }

    @Test
    public void testCriarDisciplina() throws Exception {
        DisciplinaDTO disciplinaDTO = new DisciplinaDTO(1L, "Matemática", "MAT101", "Prof. João", 1L);
        when(disciplinaService.salvarDisciplina(any(DisciplinaDTO.class))).thenReturn(disciplinaDTO);

        mockMvc.perform(post("/api/disciplinas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(disciplinaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Matemática"))
                .andExpect(jsonPath("$.codigoTurma").value("MAT101"))
                .andExpect(jsonPath("$.nomeProfessor").value("Prof. João"))
                .andExpect(jsonPath("$.professorId").value(1L));
    }

    @Test
    public void testAtualizarDisciplina() throws Exception {
        DisciplinaDTO disciplinaDTO = new DisciplinaDTO(1L, "Matemática", "MAT101", "Prof. João", 1L);
        when(disciplinaService.atualizarDisciplina(eq(1L), any(DisciplinaDTO.class))).thenReturn(disciplinaDTO);

        mockMvc.perform(put("/api/disciplinas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(disciplinaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Matemática"))
                .andExpect(jsonPath("$.codigoTurma").value("MAT101"))
                .andExpect(jsonPath("$.nomeProfessor").value("Prof. João"))
                .andExpect(jsonPath("$.professorId").value(1L));
    }

    @Test
    public void testExcluirDisciplina() throws Exception {
        doNothing().when(disciplinaService).excluirDisciplina(1L);

        mockMvc.perform(delete("/api/disciplinas/1"))
                .andExpect(status().isNoContent());
    }
}