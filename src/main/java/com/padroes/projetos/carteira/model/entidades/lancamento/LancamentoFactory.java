package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;

import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;

public interface LancamentoFactory {

    public Lancamento criarLancamento(Usuario user, BigDecimal valor, String msg, Operacao operacao);

}