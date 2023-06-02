package com.padroes.projetos.carteira.model.excecoes;

public class EntidadeNaoCadastradaException extends RuntimeException {

    public EntidadeNaoCadastradaException(String msg) {
        super(msg);
    }

}
