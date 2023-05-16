package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;

public abstract class Lancamento {

    protected Long id;
    protected Usuario usuario;
    protected String mensagem;
    protected BigDecimal valor;
    protected Operacao operacao;
    protected LocalDateTime dataHoraLancamento;

    public Lancamento() {
    }

    public Lancamento(Usuario usuario, String mensagem, Operacao operacao,
            LocalDateTime dataHoraLancamento) {
        this.dataHoraLancamento = dataHoraLancamento;
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.operacao = operacao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public LocalDateTime getDataHoraLancamento() {
        return dataHoraLancamento;
    }

    public void setDataHoraLancamento(LocalDateTime dataHoraLancamento) {
        this.dataHoraLancamento = dataHoraLancamento;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
