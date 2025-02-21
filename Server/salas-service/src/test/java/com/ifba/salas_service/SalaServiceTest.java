package com.ifba.salas_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ifba.salas_service.dtos.SalaDTO;
import com.ifba.salas_service.exceptions.SalaNotFoundException;
import com.ifba.salas_service.mappers.SalaMapper;
import com.ifba.salas_service.models.Sala;
import com.ifba.salas_service.repositories.SalaRepository;
import com.ifba.salas_service.services.SalaService;

@SpringBootTest
public class SalaServiceTest {

    @Mock
    private SalaRepository salaRepository;

    @InjectMocks
    private SalaService salaService;
    

    @Test
    public void testListarSalas() {
        List<Sala> salas = new ArrayList<>();
        salas.add(new Sala(1L, "Sala 101"));
        salas.add(new Sala(2L, "Sala 102"));
        when(salaRepository.findAll()).thenReturn(salas);

        List<SalaDTO> salaDTOs = salaService.listarSalas();

        assertEquals(2, salaDTOs.size());
        assertEquals(1L, salaDTOs.get(0).getId());
        assertEquals("Sala 101", salaDTOs.get(0).getNome());
        assertEquals(2L, salaDTOs.get(1).getId());
        assertEquals("Sala 102", salaDTOs.get(1).getNome());
    }

    @Test
    public void testBuscarPorId() {
        Sala sala = new Sala(1L, "Sala 101");
        when(salaRepository.findById(1L)).thenReturn(Optional.of(sala));

        SalaDTO salaDTO = salaService.buscarPorId(1L);

        assertEquals(1L, salaDTO.getId());
        assertEquals("Sala 101", salaDTO.getNome());
    }

    @Test
    public void testBuscarPorIdNotFound() {
        when(salaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SalaNotFoundException.class, () -> {
            salaService.buscarPorId(1L);
        });
    }

    @Test
    public void testSalvarSala() {
        SalaDTO salaDTO = new SalaDTO(1L, "Sala 101");
        Sala sala = new Sala(1L, "Sala 101");
        when(salaRepository.save(any(Sala.class))).thenReturn(sala);

        SalaDTO savedSalaDTO = salaService.salvarSala(salaDTO);

        assertEquals(1L, savedSalaDTO.getId());
        assertEquals("Sala 101", savedSalaDTO.getNome());
    }

    @Test
    public void testDeletarSala() {
        Long id = 1L;

        assertDoesNotThrow(() -> {
            salaService.deletarSala(id);
        });

        verify(salaRepository, times(1)).deleteById(id);
    }
}

