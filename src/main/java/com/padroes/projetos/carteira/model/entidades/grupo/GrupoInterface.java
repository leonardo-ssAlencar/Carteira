package com.padroes.projetos.carteira.model.entidades.grupo;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class GrupoInterface {

    public static GrupoComponent grupoRaiz = new GrupoComponent() {
        @Override
        public String getNome() {
            return "Raiz";
        }

        @Override
        public Optional<GrupoComponent> getParente() {
            return Optional.empty();
        }

    };

    public Grupo criarGrupoUsuario(Usuario usuario) {

        Grupo grupo = new Grupo();
        grupo.setParente(grupoRaiz);
        grupo.setNome(usuario.getNome());
        grupo.setDono(usuario);

        return grupo;

    }

    public Grupo criarGrupo(String nome, Grupo parente, GrupoComponent dono) {
        Grupo grupo = new Grupo();
        grupo.setDono(dono);
        grupo.setNome(nome);
        grupo.setParente(parente);
        grupo.setParticipantes(dono);
        if (dono instanceof Usuario) {
            grupo.tornarAdmin((Usuario) dono);
            return grupo;

        }

        Grupo grupoPai = (Grupo) dono;

        grupo.setParticipantes(grupoPai.getAdministradores());
        for (Usuario user : grupoPai.getAdministradores()) {
            grupo.tornarAdmin(user);
        }

        return grupo;

    }

}
