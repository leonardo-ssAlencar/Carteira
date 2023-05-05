package com.padroes.projetos.carteira.model.entidades.excecoes;

public class OperacaoNaoPermitidaException extends RuntimeException {

    public OperacaoNaoPermitidaException(String msg) {
        super(msg);
    }

}
