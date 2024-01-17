package com.devalvesg.desafiouolhost.Controllers;


import com.devalvesg.desafiouolhost.DTOs.JogadorDTO;
import com.devalvesg.desafiouolhost.Services.JogadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {
    public JogadorController (JogadorService jogadorService){
        this.jogadorService = jogadorService;
    }

    private JogadorService jogadorService;

    @PostMapping("novo-jogador")
    public ResponseEntity novoJogador(@RequestBody @Valid JogadorDTO jogadorDTO){
        try{
            jogadorService.novoJogador(jogadorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("O Cadastro do usuário foi realizado corretamente");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
