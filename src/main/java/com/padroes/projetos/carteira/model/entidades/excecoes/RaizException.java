package com.padroes.projetos.carteira.model.entidades.excecoes;

public class RaizException extends RuntimeException {

    private String mensagem = "Chegou a raiz ";

    public RaizException(String msg) {
        this.mensagem += msg;

    }

    public String getMensagem() {
        return mensagem;
    }

}
