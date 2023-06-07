package com.padroes.projetos.carteira.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.Participante;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsuarioController {

    @Autowired
    AplicacaoFachada fachada;
    @Autowired
    ControllerService service;

    @GetMapping("/usuario")
    public String telaUsuario(HttpServletRequest request, Model model) {
        Usuario user = (Usuario) request.getSession().getAttribute("userLogado");
        Grupo grupo = (Grupo) request.getSession().getAttribute("grupoUser");
        if (user == null) {
            return "redirect:/";
        }

        List<Lancamento> lancamentos = fachada.lancamentos(user);
        List<Participante> participantes = fachada.participantes(grupo);

        BigDecimal valor = service.calcularValorTotal(lancamentos.stream().map(Lancamento::getValor).toList());

        model.addAttribute("valorTotal", valor);
        model.addAttribute("user", user);
        model.addAttribute("participantes", participantes);
        model.addAttribute("lancamentos", lancamentos);

        return "usuario";

    }

}
