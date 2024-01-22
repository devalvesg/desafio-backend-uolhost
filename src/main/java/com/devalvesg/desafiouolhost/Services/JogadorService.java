package com.devalvesg.desafiouolhost.Services;

import com.devalvesg.desafiouolhost.DTOs.JogadorDTO;
import com.devalvesg.desafiouolhost.DTOs.Responses.Vingador;
import com.devalvesg.desafiouolhost.DTOs.Responses.VingadoresResponse;
import com.devalvesg.desafiouolhost.Entities.Grupo;
import com.devalvesg.desafiouolhost.Entities.Jogador;
import com.devalvesg.desafiouolhost.Repositories.JogadorRepositorie;
import com.devalvesg.desafiouolhost.Services.ExternalServices.CodinomeJson;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JogadorService {

    private JogadorRepositorie jogadorRepositorie;
    private static CodinomeJson codinomeJson;
    public JogadorService(JogadorRepositorie jogadorRepositorie, CodinomeJson codinomeJson){
        this.jogadorRepositorie = jogadorRepositorie;
        this.codinomeJson = codinomeJson;
    }


    public List<Jogador> findAll(){
        return jogadorRepositorie.findAll();
    }


    public void novoJogador(JogadorDTO jogadorDTO) throws Exception {
        Jogador novoJogador = new Jogador(jogadorDTO);
        String extensão = jogadorDTO.grupo() == Grupo.VINGADORES ? ".json" : ".xml";
        String codinome = codinomeJson.buscaCodinameJson(novoJogador.getGrupo().toString(), extensão);
        novoJogador.setCodinome(codinome);
        jogadorRepositorie.save(novoJogador);


    }

    public Jogador findById(Long id) throws Exception {
        Optional<Jogador> resultado = jogadorRepositorie.findById(id);
        return resultado.orElseThrow(() -> new Exception("Usuario não encontrado em nossa base de dados!"));

    }

    public void deleteById(Long id) throws Exception {
        try{
            jogadorRepositorie.deleteById(id);
        }catch (Exception e){
            throw new Exception("Usuario não encontrado em nossa base de dados!");
        }
    }
}
