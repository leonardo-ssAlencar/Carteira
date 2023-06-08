package com.padroes.projetos.carteira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.padroes.projetos.carteira.model.entidades.grupo.Participante;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ParticipanteController {

    @Autowired
    private AplicacaoFachada fachada;

    @GetMapping("/grupo/{id}/remover_participante/{pId}")
    public String removerParticipante(HttpServletRequest request, @PathVariable("id") Long gId,
            @PathVariable("pId") Long pId) {

        Usuario user = (Usuario) request.getSession().getAttribute("userLogado");
        if (user == null) {
            return "redirect:/";
        }

        Participante participante = fachada.buscarParticipante(pId);

        fachada.deletarParticipante(participante);

        return "redirect:/grupo/" + gId;

    }

    @GetMapping("/grupo/{id}/tornar_administrador/{pId}")
    public String tornarAdmin(HttpServletRequest request, @PathVariable("id") Long gId,
            @PathVariable("pId") Long pId) {

        Usuario user = (Usuario) request.getSession().getAttribute("userLogado");
        if (user == null) {
            return "redirect:/";
        }

        Participante participante = fachada.buscarParticipante(pId);

        participante.tornarAdmin(!participante.eAdmin());

        fachada.salvarParticipante(participante);

        return "redirect:/grupo/" + gId;

    }

}
