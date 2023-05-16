package com.padroes.projetos.carteira.model.entidades.commands;

import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;

public abstract class LancamentoCommand {
    private Grupo grupo;

    public LancamentoCommand(Grupo grupo) {
        this.grupo = grupo;

    }

    public Grupo getGrupo() {
        return grupo;
    }

    public abstract void executar();

    public abstract Lancamento getLancamento();

}
