package com.padroes.projetos.carteira.model.entidades.commands;

import java.math.BigDecimal;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.lancamento.Debito;

public class DebitoCommand extends LancamentoCommand {

    public DebitoCommand(Usuario user, Caixinha caixinha, BigDecimal valor) {
        super(user, caixinha, valor);
        this.operacao = new Debito();

    }

    @Override
    public void executar() {
        caixinha.fazerLancamento(this);

    }

}
