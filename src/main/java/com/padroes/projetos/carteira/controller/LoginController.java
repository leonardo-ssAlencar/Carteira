package com.padroes.projetos.carteira.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.excecoes.UsuarioNaoExisteException;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private AplicacaoFachada fachada;

    @PostMapping("/fazerLogin")
    public String fazerLogin(@Param("email") String email, @Param("senha") String senha, HttpServletRequest request) {

        Optional<Usuario> user = fachada.validarUsuario(email, senha);

        if (user.isEmpty()) {
            throw new UsuarioNaoExisteException("O Usuario não existe");
        }

        request.getSession().setAttribute("userLogado", user.get());
        request.getSession().setAttribute("grupoUser", user.get().getParente());

        return "redirect:/usuario";

    }

}
