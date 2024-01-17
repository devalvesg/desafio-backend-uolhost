package com.devalvesg.desafiouolhost.DTOs.Responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "liga_da_justica")
public class VingadoresResponse {

    @JacksonXmlElementWrapper(localName = "codinomes")
    @JacksonXmlProperty(localName = "codinome")
    private List<Vingador> vingadores;

//    public VingadoresResponse(String codinome) {
//        Vingador vingador = new Vingador(codinome);
//        vingadores.add(vingador);
//    }

}
