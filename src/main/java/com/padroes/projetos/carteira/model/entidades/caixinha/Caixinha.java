package com.padroes.projetos.carteira.model.entidades.caixinha;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.commands.LancamentoCommand;
import com.padroes.projetos.carteira.model.entidades.enuns.TipoLancamento;
import com.padroes.projetos.carteira.model.entidades.estrategiaEstorno.EstrategiaEstorno;
import com.padroes.projetos.carteira.model.entidades.estrategiaLancamento.LancamentoEstrategy;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.model.entidades.notificacao.EstrategiaNotificacao;

public class Caixinha {

    private Long id;
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

    public void executarLancamento(LancamentoCommand command) {
        Lancamento lancamento = lancamentoEstrategy.executar(this, command);
        lancamentos.add(lancamento);
    }

    public void notificar(String mensagem) {
        this.notificador.notificar(mensagem, this);

    }

    public void realizarEstorno() {
        this.estorno.calcularExtorno(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public LancamentoEstrategy getLancamentoEstrategy() {
        return lancamentoEstrategy;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public List<TipoLancamento> getProibidos() {
        return proibidos;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void addItems(List<Item> items) {
        this.itens.addAll(items);

    }

    public EstrategiaNotificacao getNotificador() {
        return notificador;
    }

    public EstrategiaEstorno getEstorno() {
        return estorno;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getMeta() {
        return meta;
    }

    public LocalDate getFechamento() {
        return fechamento;
    }

    public boolean isMensal() {
        return mensal;
    }

}
