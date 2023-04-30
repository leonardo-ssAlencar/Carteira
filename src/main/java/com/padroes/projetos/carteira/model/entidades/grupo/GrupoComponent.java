package com.padroes.projetos.carteira.model.entidades.grupo;

import java.util.Optional;

public interface GrupoComponent {

    public Optional<GrupoComponent> getParente();

    public String getNome();

}
