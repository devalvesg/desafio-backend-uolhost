package com.devalvesg.desafiouolhost.Infra;

import com.devalvesg.desafiouolhost.DTOs.Responses.Vingador;
import com.devalvesg.desafiouolhost.DTOs.Responses.VingadoresResponse;
import com.devalvesg.desafiouolhost.Repository.JogadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CodinomeServiceTest {

    @Mock
    private JogadorRepository jogadorRepository;
    @InjectMocks
    private CodinomeService codinomeService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @DisplayName("Validação do codinome")
    @Test
    public void testValidacaoCodinome() throws Exception {
        VingadoresResponse responseMock = mock(VingadoresResponse.class);
        Vingador vingadorMock1 = mock(Vingador.class);
        Vingador vingadorMock2 = mock(Vingador.class);

        when(responseMock.getVingadores()).thenReturn(Arrays.asList(vingadorMock1, vingadorMock2));
        when(vingadorMock1.getCodinome()).thenReturn("Homem de Ferro");
        when(vingadorMock2.getCodinome()).thenReturn("Capitão América");

        String result = codinomeService.validacaoCodinome(responseMock);

        assertEquals("Homem de Ferro", result);
    }


}
