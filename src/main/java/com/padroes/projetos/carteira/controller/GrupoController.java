package com.padroes.projetos.carteira.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.padroes.projetos.carteira.model.entidades.caixinha.CaixinhaBuilder;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoFachada;
import com.padroes.projetos.carteira.model.entidades.grupo.Participante;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GrupoController {

    @Autowired
    private AplicacaoFachada fachada;
    @Autowired
    private ControllerService service;
    @Autowired
    private GrupoFachada gFachada;

    @GetMapping("/grupo/{id}")
    public String grupo(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        Usuario user = (Usuario) request.getSession().getAttribute("userLogado");
        if (user == null) {
            return "redirect:/";
        }

        Grupo grupoAtual = fachada.buscarGrupo(id);

        Participante userLogparticipante = null;

        for (Participante p : grupoAtual.getParticipantes()) {

            if (p.getParticipante().equals(user)) {
                userLogparticipante = p;
                break;
            }
        }

        if (userLogparticipante == null) {
            return "redirect:/usuario";

        }

        BigDecimal valorTotal = service.calcularValorTotal(
                grupoAtual.getCaixinha().getLancamentos().stream().map(Lancamento::getValor).toList());

        model.addAttribute("grupo", grupoAtual);
        model.addAttribute("saldo", valorTotal);
        model.addAttribute("userLogParticipante", userLogparticipante);
        model.addAttribute("participantes", grupoAtual.getParticipantes());
        model.addAttribute("lancamentos", grupoAtual.getCaixinha().getLancamentos());

        return "grupo";

    }

    @GetMapping("/grupo/{id}/add_participante")
    public String addParticipante(HttpServletRequest request, Model model, @PathVariable("id") Long id) {
        Usuario user = (Usuario) request.getSession().getAttribute("userLogado");
        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("grupoId", id);

        return "adicionar_participante";
    }

    @PostMapping("/grupo/{id}/add_participante")
    public String addParticipante(HttpServletRequest request, @PathVariable("id") Long id,
            @RequestParam("email") String email) {
        Usuario user = (Usuario) request.getSession().getAttribute("userLogado");
        if (user == null) {
            return "redirect:/";
        }

        Participante participante = fachada.buscarUsuario(email);
        Grupo grupo = fachada.buscarGrupo(id);

        fachada.cadastrarParticipante(participante.getParticipante(), grupo);

        return "redirect:/grupo/" + id;

    }

    @GetMapping("/grupo/{id}/remover_grupo")
    public String deletarGrupo(HttpServletRequest request, @PathVariable("id") Long id) {
        Usuario user = (Usuario) request.getSession().getAttribute("userLogado");
        if (user == null) {
            return "redirect:/";
        }
        Grupo grupo = fachada.buscarGrupo(id);

        Participante participante = fachada.participante(user, grupo);

        Participante gParticipante = fachada.participante(grupo, participante.getGrupo());

        fachada.deletarParticipante(participante);
        fachada.deletarParticipante(gParticipante);

        return "redirect:/usuario";
    }

    @GetMapping("/grupo/{id}/sair")
    public String sairDoGrupo(HttpServletRequest request, @PathVariable("id") Long id) {
        Usuario user = (Usuario) request.getSession().getAttribute("userLogado");
        Grupo grupoUser = (Grupo) request.getSession().getAttribute("grupoUser");
        if (user == null) {
            return "redirect:/";
        }

        Grupo grupo = fachada.buscarGrupo(id);

        Participante participante = fachada.participante(user, grupo);
        Participante gParticipante = fachada.participante(grupo, grupoUser);

        fachada.deletarParticipante(participante);
        fachada.deletarParticipante(gParticipante);

        request.getSession().setAttribute("grupoUser", fachada.buscarGrupo(grupoUser.getId()));

        return "redirect:/usuario";

    }

    @GetMapping("/novo_grupo")
    public String nGrupo(HttpServletRequest request) {
        Usuario user = (Usuario) request.getSession().getAttribute("userLogado");
        if (user == null) {
            return "redirect:/";
        }
        return "novo_grupo";
    }

    @PostMapping("/novo_grupo")
    public String novoGrupo(HttpServletRequest request, @RequestParam("nomeGrupo") String nomeGrupo) {
        Usuario user = (Usuario) request.getSession().getAttribute("userLogado");
        if (user == null) {
            return "redirect:/";
        }

        Grupo grupo = gFachada.criarGrupo(nomeGrupo, user);
        CaixinhaBuilder builder = new CaixinhaBuilder();

        grupo.setCaixinha(builder.build());

        fachada.cadastrarGrupo(grupo);
        return "redirect:/usuario";
    }

}
