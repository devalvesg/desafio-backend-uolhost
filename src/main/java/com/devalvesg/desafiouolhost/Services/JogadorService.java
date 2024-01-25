package com.devalvesg.desafiouolhost.Services;

import com.devalvesg.desafiouolhost.DTOs.JogadorDTO;
import com.devalvesg.desafiouolhost.Entities.Grupo;
import com.devalvesg.desafiouolhost.Entities.Jogador;
import com.devalvesg.desafiouolhost.Repository.JogadorRepository;
import com.devalvesg.desafiouolhost.Infra.CodinomeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JogadorService {

    private JogadorRepository jogadorRepository;
    private static CodinomeService codinomeService;
    public JogadorService(JogadorRepository jogadorRepository, CodinomeService codinomeService){
        this.jogadorRepository = jogadorRepository;
        this.codinomeService = codinomeService;
    }


    public List<Jogador> findAll(){
        return jogadorRepository.findAll();
    }


    public void novoJogador(JogadorDTO jogadorDTO) throws Exception {
        Jogador novoJogador = new Jogador(jogadorDTO);
        String extensao = jogadorDTO.grupo() == Grupo.VINGADORES ? ".json" : ".xml";
        String codinome = codinomeService.buscaCodiname(novoJogador.getGrupo().toString(), extensao);
        novoJogador.setCodinome(codinome);
        jogadorRepository.save(novoJogador);


    }

    public Jogador findById(Long id) throws Exception {
        Optional<Jogador> resultado = jogadorRepository.findById(id);
        return resultado.orElseThrow(() -> new Exception("Usuario não encontrado em nossa base de dados!"));

    }

    public void deleteById(Long id) throws Exception {
        if(!jogadorRepository.existsById(id)){
            throw new Exception("Usuario não encontrado em nossa base de dados!");
        }
        jogadorRepository.deleteById(id);
    }
}
