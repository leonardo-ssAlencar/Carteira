package com.padroes.projetos.carteira.model.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoComponent;

public class Caixinha {

    private Long id;
    private EstrategiaDevolucao estrategiaDevolucao;
    private List<TipoLancamento> lancamentos;
    private EstrategiaNotificacao estrategiaNotificacao;
    private Grupo grupo;
    private LocalDate fechamento;
    private BigDecimal valorTotal;

    public Caixinha() {

    }

    public void notificar(GrupoComponent... usuarios) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstrategiaDevolucao getEstrategiaDevolucao() {
        return estrategiaDevolucao;
    }

    public void setEstrategiaDevolucao(EstrategiaDevolucao estrategiaDevolucao) {
        this.estrategiaDevolucao = estrategiaDevolucao;
    }

    public List<TipoLancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<TipoLancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public EstrategiaNotificacao getEstrategiaNotificacao() {
        return estrategiaNotificacao;
    }

    public void setEstrategiaNotificacao(EstrategiaNotificacao estrategiaNotificacao) {
        this.estrategiaNotificacao = estrategiaNotificacao;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public LocalDate getFechamento() {
        return fechamento;
    }

    public void setFechamento(LocalDate fechamento) {
        this.fechamento = fechamento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

}
