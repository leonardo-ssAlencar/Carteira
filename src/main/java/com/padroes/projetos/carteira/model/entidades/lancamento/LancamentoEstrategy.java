package com.padroes.projetos.carteira.model.entidades.lancamento;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.grupo.comandos.LancamentoCommand;

public interface LancamentoEstrategy {

    public Lancamento processarLancamento(Caixinha caixinha, LancamentoCommand command);

}
