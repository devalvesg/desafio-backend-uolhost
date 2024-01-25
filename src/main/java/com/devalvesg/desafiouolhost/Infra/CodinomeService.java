package com.devalvesg.desafiouolhost.Infra;

import com.devalvesg.desafiouolhost.DTOs.Responses.Vingador;
import com.devalvesg.desafiouolhost.DTOs.Responses.VingadoresResponse;
import com.devalvesg.desafiouolhost.Entities.Grupo;
import com.devalvesg.desafiouolhost.Repository.JogadorRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class CodinomeService {

    private JogadorRepository jogadorRepository;
    private List<String> codinomes = new ArrayList<>();
    public CodinomeService(JogadorRepository jogadorRepository){
        this.jogadorRepository = jogadorRepository;
        this.codinomes = jogadorRepository.findAllCodinames();
    }
    public String buscaCodiname(String grupo, String tipo) throws Exception {
        String urlParaChamada = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/" + grupo.toLowerCase() + tipo;

        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != 200)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader respostaAPI = new BufferedReader(new InputStreamReader((conexao.getInputStream())));

            if(grupo == Grupo.VINGADORES.toString()){
                VingadoresResponse respostaConvertida = converterJson(respostaAPI);
                String respostaFinal = validacaoCodinome(respostaConvertida);
                codinomes.add(respostaFinal);
                return respostaFinal;
            }

            VingadoresResponse respostaConvertida = converterXML(respostaAPI);
            String respostaFinal = validacaoCodinome(respostaConvertida);
            codinomes.add(respostaFinal);
            return respostaFinal;


        } catch (Exception e) {
            throw new Exception("A lista escolhida não possui mais codinomes disponíveis!");
        }

    }
    public VingadoresResponse converterJson(BufferedReader buffereReader) throws IOException {
        String resposta, jsonEmString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonEmString += resposta;
        }
        Gson gson = new Gson();
        VingadoresResponse response = gson.fromJson(jsonEmString, VingadoresResponse.class);
        return response;
    }

    public VingadoresResponse converterXML(BufferedReader buffereReader) throws IOException, JAXBException, JAXBException {
        StringBuilder xmlResponse = new StringBuilder();
        String line;
        while ((line = buffereReader.readLine()) != null) {
            xmlResponse.append(line);
        }

        String xmlData = xmlResponse.toString();
        XmlMapper xmlMapper = new XmlMapper();
        VingadoresResponse response = xmlMapper.readValue(xmlData, VingadoresResponse.class);

        return response;
    }

    public String validacaoCodinome(VingadoresResponse response) throws Exception {
        for(Vingador vingador : response.getVingadores()){
            if(!codinomes.contains(vingador.getCodinome())){
                return vingador.getCodinome();
            }
        }

        throw new Exception();
    }



}
