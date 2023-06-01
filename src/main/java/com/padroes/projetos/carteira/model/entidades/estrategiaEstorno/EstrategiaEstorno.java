package com.padroes.projetos.carteira.model.entidades.estrategiaEstorno;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

public abstract class EstrategiaEstorno {

    private Long id;

    public abstract void calcularExtorno(Caixinha caixinha);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
