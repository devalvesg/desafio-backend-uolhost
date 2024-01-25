package com.devalvesg.desafiouolhost.Services;

import com.devalvesg.desafiouolhost.DTOs.JogadorDTO;
import com.devalvesg.desafiouolhost.Entities.Grupo;
import com.devalvesg.desafiouolhost.Entities.Jogador;
import com.devalvesg.desafiouolhost.Infra.CodinomeService;
import com.devalvesg.desafiouolhost.Repository.JogadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class JogadorServiceTest {

    @Mock
    private JogadorRepository jogadorRepository;

    @Mock
    private CodinomeService codinomeService;
    @InjectMocks
    private JogadorService jogadorService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testNovoJogador() throws Exception {
        JogadorDTO jogadorDTO = new JogadorDTO("Gabriel", "gabriel@gmail.com", "16 999999999", Grupo.VINGADORES);
        when(codinomeService.buscaCodiname(anyString(), anyString())).thenReturn("CodinomeExemplo");
        assertDoesNotThrow(() -> jogadorService.novoJogador(jogadorDTO));
        verify(jogadorRepository, times(1)).save(any(Jogador.class));
    }

    @Test @DisplayName("Buscando jogador por ID: CORRETO")
    void testFindById() throws Exception {

//        Jogador jogadorExemplo = mock(Jogador.class);
        Jogador jogadorExemplo = new Jogador();
        jogadorExemplo.setId(1L);
        jogadorExemplo.setNome("Gabriel");
        jogadorExemplo.setEmail("Gabriel@gmail.com");
        jogadorExemplo.setTelefone("16 99999999");
        jogadorExemplo.setGrupo(Grupo.VINGADORES);

        when(jogadorRepository.findById(1L)).thenReturn(Optional.of(jogadorExemplo));

        Jogador jogadorEncontrado = assertDoesNotThrow(() -> jogadorService.findById(1L));
        assertEquals(jogadorExemplo, jogadorEncontrado);
        verify(jogadorRepository, times(1)).findById(1L);
    }

    @Test @DisplayName("Buscando Jogador por ID: ERRO")
    void testFindByIdNotFound() {

        when(jogadorRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> jogadorService.findById(2L));
        verify(jogadorRepository, times(1)).findById(2L);
    }

    @Test @DisplayName("Deletando um usuario pelo ID: ERRO")
    void deleteById(){
        when(jogadorRepository.existsById(1L)).thenReturn(false);
        assertThrows(Exception.class, () -> jogadorService.deleteById(1L));
        verify(jogadorRepository, never()).deleteById(1L);
    }

}