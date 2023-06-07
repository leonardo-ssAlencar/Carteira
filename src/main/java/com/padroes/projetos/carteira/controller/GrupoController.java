package com.padroes.projetos.carteira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.Participante;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GrupoController {

    @Autowired
    private AplicacaoFachada fachada;

    @GetMapping("/grupo/{id}")
    public String grupo(HttpServletRequest request, Model model) {
        Usuario user = (Usuario) request.getSession().getAttribute("userLogado");
        Grupo grupo = (Grupo) request.getSession().getAttribute("grupoUser");

        if (user == null) {
            return "redirect:/";
        }
        List<Participante> participantes = fachada.participantes(grupo);
        Participante participante = participantes.stream().filter(x -> x.getParticipante().equals(user)).findFirst()
                .orElseThrow();

        model.addAttribute("lParticipante", participantes);
        model.addAttribute("participante", participante);

        return "grupo";

    }

}
