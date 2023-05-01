package com.padroes.projetos.carteira.model.entidades.grupo;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class GrupoInterface {

    /**
     * Classe estatica para criação de grupos raizes
     */
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

    /**
     * Cria um grupo de usuario, ou seja um grupo que serve de raiz para todos os
     * grupos que o usuario está inserido
     * 
     * @param usuario é um tipo usuario, todo usuario deve está inserido num grupo
     *                raiz
     * @return Grupo grupo raiz
     */
    public Grupo criarGrupoUsuario(Usuario usuario) {

        Grupo grupo = new Grupo();
        grupo.setParente(grupoRaiz);
        grupo.setNome(usuario.getNome());
        grupo.setDono(usuario);
        usuario.setParente(grupo);

        return grupo;

    }

    /**
     * 
     * @param String  nome do grupo
     * @param Usuario O dono do grupo
     * @return Grupo um novo grupo
     */
    public Grupo criarGrupo(String nome, Usuario dono) {
        Grupo grupo = new Grupo();
        grupo.setDono(dono);
        grupo.setNome(nome);
        grupo.setParente(dono.getParente().get());
        grupo.setParticipantes(dono);

        Grupo rootGrupo = (Grupo) dono.getParente().get();
        rootGrupo.setParticipantes(grupo);
        grupo.tornarAdmin((Usuario) dono);

        return grupo;

    }

    public Grupo criarSubGrupo(String nome, Grupo pai) {
        Grupo grupo = new Grupo();

        grupo.setNome(nome);
        grupo.setDono(pai);
        grupo.setParente(pai);
        pai.setParticipantes(grupo);

        return grupo;

    }

}
