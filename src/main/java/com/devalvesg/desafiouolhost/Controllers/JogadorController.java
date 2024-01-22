package com.devalvesg.desafiouolhost.Controllers;


import com.devalvesg.desafiouolhost.DTOs.JogadorDTO;
import com.devalvesg.desafiouolhost.Entities.Jogador;
import com.devalvesg.desafiouolhost.Services.JogadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity findAll(){
            List<Jogador> jogadores = jogadorService.findAll();
            return ResponseEntity.ok().body(jogadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        try{
            Jogador jogador = jogadorService.findById(id);
            return ResponseEntity.ok().body(jogador);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        try{
            jogadorService.deleteById(id);
            return ResponseEntity.ok().body("O jogador foi deletado com sucesso!");
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
