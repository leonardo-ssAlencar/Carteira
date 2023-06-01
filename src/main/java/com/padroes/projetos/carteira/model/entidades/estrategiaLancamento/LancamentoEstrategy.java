package com.padroes.projetos.carteira.model.entidades.estrategiaLancamento;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.commands.LancamentoCommand;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;

public abstract class LancamentoEstrategy {

    private Long id;

    public abstract Lancamento executar(Caixinha caixinha, LancamentoCommand command);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
