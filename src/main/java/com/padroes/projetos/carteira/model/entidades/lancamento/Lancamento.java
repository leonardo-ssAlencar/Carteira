package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Lancamento {

    private Long id;
    private String descricao;
    private LocalDateTime dataHora;
    private BigDecimal valor;
    private Operacao operacao;
    private String usuario;

    public Lancamento(String descricao, LocalDateTime dataHora, BigDecimal valor, Operacao operacao, String usuario) {
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.valor = valor;
        this.operacao = operacao;
        this.usuario = usuario;
    }

    public abstract String descricao();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
