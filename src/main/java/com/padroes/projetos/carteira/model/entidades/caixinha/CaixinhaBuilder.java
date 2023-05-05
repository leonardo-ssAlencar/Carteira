package com.padroes.projetos.carteira.model.entidades.caixinha;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.EstrategiaDevolucao;
import com.padroes.projetos.carteira.model.entidades.EstrategiaNotificacao;
import com.padroes.projetos.carteira.model.entidades.TipoLancamento;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;

public final class CaixinhaBuilder {

    private EstrategiaDevolucao estrategiaDevolucao;
    private List<TipoLancamento> lancamentos;
    private EstrategiaNotificacao estrategiaNotificacao;
    private Grupo grupo;
    private LocalDate fechamento;
    private boolean mensal;
    private BigDecimal valorTotal;

    public CaixinhaBuilder diaFechamento(LocalDate data, boolean mensal) {
        if (data.isAfter(LocalDate.now())) {
            this.fechamento = data;
            this.mensal = mensal;
            return this;
        }

        throw new RuntimeException("Data invalida");

    }

    private CaixinhaBuilder() {
    }

    public static CaixinhaBuilder builder() {
        return new CaixinhaBuilder();
    }

    public CaixinhaBuilder lancamentos(TipoLancamento... lancamentos) {
        this.lancamentos = new ArrayList<>();

        if (lancamentos == null) {
            return this;

        }
        this.lancamentos.addAll(Arrays.asList(lancamentos));

        return this;
    }

    public CaixinhaBuilder devolucao(EstrategiaDevolucao estrategia) {
        this.estrategiaDevolucao = estrategia;
        return this;
    }

    public CaixinhaBuilder notificacao(EstrategiaNotificacao estrategia) {
        this.estrategiaNotificacao = estrategia;
        return this;
    }

    public Caixinha build() {
        return new Caixinha(estrategiaDevolucao, lancamentos, estrategiaNotificacao, grupo, fechamento, mensal,
                valorTotal);
    }

}