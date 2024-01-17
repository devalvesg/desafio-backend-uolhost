package com.devalvesg.desafiouolhost.DTOs;

import com.devalvesg.desafiouolhost.Entities.Grupo;

public record JogadorDTO(String nome, String email, String telefone, Grupo grupo) {
}
