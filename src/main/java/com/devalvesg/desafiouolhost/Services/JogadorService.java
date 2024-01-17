package com.devalvesg.desafiouolhost.Services;

import com.devalvesg.desafiouolhost.DTOs.JogadorDTO;
import com.devalvesg.desafiouolhost.DTOs.Responses.Vingador;
import com.devalvesg.desafiouolhost.DTOs.Responses.VingadoresResponse;
import com.devalvesg.desafiouolhost.Entities.Grupo;
import com.devalvesg.desafiouolhost.Entities.Jogador;
import com.devalvesg.desafiouolhost.Repositories.JogadorRepositorie;
import com.devalvesg.desafiouolhost.Services.ExternalServices.CodinomeJson;
import org.springframework.stereotype.Service;


@Service
public class JogadorService {

    private JogadorRepositorie jogadorRepositorie;
    private static CodinomeJson codinomeJson;
    public JogadorService(JogadorRepositorie jogadorRepositorie, CodinomeJson codinomeJson){
        this.jogadorRepositorie = jogadorRepositorie;
        this.codinomeJson = codinomeJson;
    }




    public void novoJogador(JogadorDTO jogadorDTO) throws Exception {
        Jogador novoJogador = new Jogador(jogadorDTO);
        String extensão = jogadorDTO.grupo() == Grupo.VINGADORES ? ".json" : ".xml";
        String codinome = codinomeJson.buscaCodinameJson(novoJogador.getGrupo().toString(), extensão);
        novoJogador.setCodinome(codinome);
        jogadorRepositorie.save(novoJogador);


    }


}
