package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;

import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;

public class LancamentoSemItemsFactory implements LancamentoFactory {

    private Lancamento lancamento;

    public LancamentoSemItemsFactory() {
        lancamento = new LancamentoSemItems();

    }

    @Override
    public Lancamento criarLancamento(Usuario user, BigDecimal valor, String msg, Operacao operacao) {
        lancamento.usuario = user;
        lancamento.valor = valor;
        lancamento.mensagem = msg;
        lancamento.operacao = operacao;

        return this.lancamento;
    }

}
