package com.devalvesg.desafiouolhost.Entities;

import com.devalvesg.desafiouolhost.DTOs.JogadorDTO;
import com.devalvesg.desafiouolhost.DTOs.Responses.Vingador;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "codinome")
public class Jogador {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotBlank(message = "O campo nome não pode ser vazio")
    private String nome;
    @NotBlank(message = "O campo email não pode ser vazio")
    private String email;
    private String telefone;
    private String codinome;
    @Enumerated(EnumType.STRING)
    private Grupo grupo;


    public Jogador(JogadorDTO jogadorDTO){
        this.nome = jogadorDTO.nome();
        this.email = jogadorDTO.email();
        this.telefone = jogadorDTO.telefone();
        this.grupo = jogadorDTO.grupo();
    }
}
