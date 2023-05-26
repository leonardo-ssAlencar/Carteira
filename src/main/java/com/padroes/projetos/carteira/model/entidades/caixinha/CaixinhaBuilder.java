package com.padroes.projetos.carteira.model.entidades.caixinha;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.stereotype.Service;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.estrategiaEstorno.EstrategiaEstorno;
import com.padroes.projetos.carteira.model.entidades.estrategiaLancamento.LancamentoSimples;
import com.padroes.projetos.carteira.model.entidades.estrategiaLancamento.LancamentoEstrategy;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.notificacao.EstrategiaNotificacao;

@Service
public class CaixinhaBuilder {

    protected List<Item> itens;
    protected EstrategiaNotificacao notificador;
    protected EstrategiaEstorno estorno;
    protected LancamentoEstrategy lancamentoEstrategy;
    protected BigDecimal valorTotal;
    protected BigDecimal meta;
    protected LocalDate fechamento;
    protected boolean mensal;

    public CaixinhaBuilder() {
        valorTotal = new BigDecimal(0);
        fechamento = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        mensal = false;
        meta = null;
        lancamentoEstrategy = new LancamentoSimples();

    }

    public CaixinhaBuilder notificador(EstrategiaNotificacao notificador) {
        this.notificador = notificador;

        return this;
    }

    public CaixinhaBuilder estorno(EstrategiaEstorno estrategy) {
        this.estorno = estrategy;

        return this;
    }

    public CaixinhaBuilder items(List<Item> items) {
        this.itens = items;

        return this;
    }

    public CaixinhaBuilder valorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;

        return this;
    }

    public CaixinhaBuilder meta(BigDecimal meta) {
        this.meta = meta;

        return this;
    }

    public CaixinhaBuilder eMensal(boolean mensal) {
        this.mensal = mensal;

        return this;
    }

    public CaixinhaBuilder fechamento(LocalDate data) {
        this.fechamento = data;
        return this;
    }

    public CaixinhaBuilder lancamento(LancamentoEstrategy estrategy) {
        this.lancamentoEstrategy = estrategy;
        return this;

    }

    public Caixinha build(Grupo grupo) {

        return new Caixinha(grupo, itens, notificador, estorno, valorTotal, meta, fechamento, mensal,
                lancamentoEstrategy);

    }

}
