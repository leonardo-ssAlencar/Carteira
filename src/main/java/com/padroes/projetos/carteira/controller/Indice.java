package com.padroes.projetos.carteira.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Indice {

    @GetMapping("/")
    public String paginaInicial() {
        return "inicial";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @GetMapping("/login")
    public String login() {
        return "login";

    }

}
