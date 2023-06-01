package com.padroes.projetos.carteira.model.entidades.grupo;

import org.springframework.stereotype.Service;

@Service
public class GrupoFachada {

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
        grupo.parente = null;
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
        grupo.setNome(nome);
        grupo.parente = (dono.getParente());
        grupo.setParticipantes(dono);

        Grupo rootGrupo = (Grupo) dono.getParente();
        rootGrupo.setParticipantes(grupo);
        grupo.tornarAdmin((Usuario) dono);

        return grupo;

    }

}
