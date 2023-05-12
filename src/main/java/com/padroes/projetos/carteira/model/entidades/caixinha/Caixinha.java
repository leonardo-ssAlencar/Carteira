package com.padroes.projetos.carteira.model.entidades.caixinha;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.LancamentoEstrategy;
import com.padroes.projetos.carteira.model.entidades.commands.LancamentoCommand;
import com.padroes.projetos.carteira.model.entidades.enuns.TipoLancamento;
import com.padroes.projetos.carteira.model.entidades.estorno.EstrategiaEstorno;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.model.entidades.notificacao.EstrategiaNotificacao;

public class Caixinha {

    protected Grupo grupo;
    protected LancamentoEstrategy lancamentoEstrategy;
    protected List<Lancamento> lancamentos = new ArrayList<>();
    protected List<TipoLancamento> proibidos;
    protected List<Item> itens;
    protected EstrategiaNotificacao notificador;
    protected EstrategiaEstorno estorno;
    protected BigDecimal valorTotal;
    protected BigDecimal meta;
    protected LocalDate fechamento;
    protected boolean mensal;

    protected Caixinha(Grupo grupo, List<TipoLancamento> permitidos, List<Item> itens,
            EstrategiaNotificacao notificador, EstrategiaEstorno estorno, BigDecimal valorTotal, BigDecimal meta,
            LocalDate fechamento, boolean mensal, LancamentoEstrategy lancamentoEstrategy) {
        this.grupo = grupo;

        this.proibidos = permitidos;
        this.itens = itens;
        this.notificador = notificador;
        this.estorno = estorno;
        this.valorTotal = valorTotal;
        this.meta = meta;
        this.fechamento = fechamento;
        this.mensal = mensal;
        this.lancamentoEstrategy = lancamentoEstrategy;
    }

    public Caixinha() {

    }

    public void fazerLancamento(LancamentoCommand comando) {

        lancamentos.add(lancamentoEstrategy.executar(this, comando));

    }

}
