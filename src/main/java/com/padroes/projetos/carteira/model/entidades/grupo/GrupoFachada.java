package com.padroes.projetos.carteira.model.entidades.grupo;

import org.springframework.stereotype.Service;

@Service
public class GrupoFachada {

    /**
     * Classe estatica para criação de grupos raizes
     */
    public static GrupoComponent grupoRaiz = new GrupoComponent() {
        @Override
        public String getNome() {
            return "Raiz";
        }

        @Override
        public GrupoComponent getParente() {
            return this;

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
        grupo.parente = grupoRaiz;
        grupo.nome = usuario.getNome();
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
        grupo.nome = nome;
        grupo.parente = (dono.getParente());
        grupo.setParticipantes(dono);

        Grupo rootGrupo = (Grupo) dono.getParente();
        rootGrupo.setParticipantes(grupo);
        grupo.tornarAdmin((Usuario) dono);

        return grupo;

    }

}
