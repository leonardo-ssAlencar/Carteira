package com.padroes.projetos.carteira.model.entidades.estrategiaLancamento;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.commands.LancamentoCommand;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;

public class LancamentoSimples implements LancamentoEstrategy {

    @Override
    public Lancamento executar(Caixinha caixinha, LancamentoCommand command) {
        Lancamento lancamento = command.getLancamento();

        lancamento.setValor(lancamento.getOperacao().executarOperacao(caixinha));

        return lancamento;

    }

}
