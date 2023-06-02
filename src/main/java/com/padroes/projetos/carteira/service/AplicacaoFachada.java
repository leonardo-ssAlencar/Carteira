package com.padroes.projetos.carteira.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.caixinha.CaixinhaBuilder;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoFachada;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.repository.RepositorioCaixinha;
import com.padroes.projetos.carteira.repository.RepositorioGrupo;
import com.padroes.projetos.carteira.repository.RepositorioUsuario;

@Service
public class AplicacaoFachada {

    @Autowired
    RepositorioUsuario usuarioRepo;
    @Autowired
    RepositorioGrupo grupoRepo;
    @Autowired
    RepositorioCaixinha caixinhaRepo;

    @Autowired
    GrupoFachada fachada;

    public Usuario cadastrarUsuario(Usuario usuario) {

        usuarioRepo.save(usuario);

        Grupo grupo = fachada.criarGrupoUsuario(usuario);

        CaixinhaBuilder builder = new CaixinhaBuilder();

        Caixinha caixinha = builder.build();
        grupo.setCaixinha(caixinha);

        caixinhaRepo.save(caixinha);

        grupoRepo.save(grupo);

        return usuarioRepo.save(usuario);
    }

    public Optional<Usuario> validarUsuario(String email, String senha) {

        Optional<Usuario> userOpt = usuarioRepo.findOneByEmail(email);

        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            if (user.getSenha().equals(senha)) {
                return userOpt;
            }
        }

        return Optional.empty();

    }

}
