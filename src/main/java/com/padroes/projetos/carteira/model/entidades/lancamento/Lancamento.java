package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @OneToOne(fetch = FetchType.LAZY)
    protected Usuario usuario;
    protected String mensagem;
    protected BigDecimal valor;
    @OneToOne
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
