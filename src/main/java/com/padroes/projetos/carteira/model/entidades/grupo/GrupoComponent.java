package com.padroes.projetos.carteira.model.entidades.grupo;

import java.util.Optional;

public interface GrupoComponent {

    /**
     * O metodo deve retornar o objeto que contem o objeto que chamou o metodo. No
     * caso de um usuario deve retornar o grupo raiz dele, no caso de um subGrupo
     * deve retornar o grupo pai dele
     * 
     * @return Optional<GrupoComponent> parente
     */
    public Optional<GrupoComponent> getParente();

    public String getNome();

}
