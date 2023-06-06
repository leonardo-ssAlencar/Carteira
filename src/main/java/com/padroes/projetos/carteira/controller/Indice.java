package com.padroes.projetos.carteira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.padroes.projetos.carteira.model.entidades.grupo.GrupoFachada;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.repository.RepositorioLancamento;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

@Controller
public class Indice {

    @Autowired
    GrupoFachada grupoFachada;
    @Autowired
    AplicacaoFachada fachada;
    @Autowired
    RepositorioLancamento repo;

    @GetMapping("/l")
    public String homePag(Model model) {
        List<Lancamento> lancamentos = repo.findAll();
        model.addAttribute("lancamentos", lancamentos);

        return "lancamentos";

    }

}
