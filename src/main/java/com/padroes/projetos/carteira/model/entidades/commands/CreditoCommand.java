package com.padroes.projetos.carteira.model.entidades.commands;

import java.math.BigDecimal;

import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.lancamento.Credito;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.model.entidades.lancamento.LancamentoFactory;

public class CreditoCommand extends LancamentoCommand {

    private Lancamento lancamento;

    public CreditoCommand(Grupo grupo, Usuario user, BigDecimal valor, String descricao, LancamentoFactory factory) {
        super(grupo);

        this.lancamento = factory.criarLancamento(user, valor, descricao, new Credito(valor));

    }

    @Override
    public Lancamento getLancamento() {
        return this.lancamento;
    }

    @Override
    public void executar() {
        getGrupo().getCaixinha().executarLancamento(this);
    }

}
