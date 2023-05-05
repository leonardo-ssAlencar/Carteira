package com.padroes.projetos.carteira.model.entidades.caixinha;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.EstrategiaDevolucao;
import com.padroes.projetos.carteira.model.entidades.EstrategiaNotificacao;
import com.padroes.projetos.carteira.model.entidades.TipoLancamento;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;

public class Caixinha {

    private Long id;
    private EstrategiaDevolucao estrategiaDevolucao;
    private List<TipoLancamento> lancamentos;
    private EstrategiaNotificacao estrategiaNotificacao;
    private Grupo grupo;
    private LocalDate fechamento;
    private boolean mensal;
    private BigDecimal valorTotal;

    protected Caixinha(EstrategiaDevolucao estrategiaDevolucao, List<TipoLancamento> lancamentos,
            EstrategiaNotificacao estrategiaNotificacao, Grupo grupo, LocalDate fechamento, boolean mensal,
            BigDecimal valorTotal) {

        this.estrategiaDevolucao = estrategiaDevolucao;
        this.lancamentos = lancamentos;
        this.estrategiaNotificacao = estrategiaNotificacao;
        this.grupo = grupo;
        this.fechamento = fechamento;
        this.mensal = mensal;
        this.valorTotal = valorTotal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public EstrategiaDevolucao getEstrategiaDevolucao() {
        return estrategiaDevolucao;
    }

    public List<TipoLancamento> getLancamentos() {
        return lancamentos;
    }

    public EstrategiaNotificacao getEstrategiaNotificacao() {
        return estrategiaNotificacao;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public LocalDate getFechamento() {
        return fechamento;
    }

    public boolean isMensal() {
        return mensal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

}
