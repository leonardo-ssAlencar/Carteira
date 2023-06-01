package com.padroes.projetos.carteira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.padroes.projetos.carteira.model.entidades.grupo.GrupoFachada;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

@Controller
public class Indice {

    @Autowired
    GrupoFachada fachada;
    @Autowired
    AplicacaoFachada aplicacaoFachada;

    @GetMapping("/home")
    public String homePag() {
        return "inicial";

    }

    @GetMapping("/cadastro")
    public String cadastroPag() {

        return "cadastro";

    }

    @GetMapping("/login")
    public String loginPag() {
        return "login";

    }

}
