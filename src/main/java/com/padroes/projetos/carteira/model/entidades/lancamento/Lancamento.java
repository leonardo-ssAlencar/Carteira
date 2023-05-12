package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;

public abstract class Lancamento {

    private Long id;
    private Usuario usuario;
    private String mensagem;
    private BigDecimal valor;
    private Operacao operacao;
    private LocalDateTime dataHoraLancamento;

    public Lancamento() {
    }

    public Lancamento(Usuario usuario, String mensagem, BigDecimal valor, Operacao operacao,
            LocalDateTime dataHoraLancamento) {
        this.dataHoraLancamento = dataHoraLancamento;
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.valor = valor;
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

}
