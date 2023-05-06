package com.padroes.projetos.carteira.model.entidades.caixinha;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.EstornoEstrategy;
import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.Notificador;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.enums.TipoLancamentoEnum;
import com.padroes.projetos.carteira.model.entidades.lancamento.LancamentoEstrategy;

public class CaixinhaBuilder {

    protected Grupo grupo;
    protected List<TipoLancamentoEnum> permitidos;
    protected LancamentoEstrategy lancamentoEstrategy;
    protected List<Item> itens;
    protected Notificador notificador;
    protected EstornoEstrategy estorno;
    protected BigDecimal valorTotal;
    protected BigDecimal meta;
    protected LocalDate fechamento;
    protected boolean mensal;

    public CaixinhaBuilder(Grupo grupo) {
        valorTotal = new BigDecimal(0);
        LocalDate hoje = LocalDate.now();
        int diasDoMes = hoje.lengthOfMonth();
        int diaHoje = hoje.getDayOfMonth();
        fechamento = hoje.plusDays(diasDoMes - diaHoje);
        mensal = false;

    }

    public static CaixinhaBuilder caixinhaBuilder(Grupo grupo) {
        return new CaixinhaBuilder(grupo);
    }

    public CaixinhaBuilder lancamentosPermitidos(List<TipoLancamentoEnum> lancamentos) {
        this.permitidos = lancamentos;

        return this;
    }

    public CaixinhaBuilder notificador(Notificador notificador) {
        this.notificador = notificador;

        return this;
    }

    public CaixinhaBuilder estorno(EstornoEstrategy estrategy) {
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

    public Caixinha build() {

        return new Caixinha(grupo, permitidos, itens, notificador, estorno, valorTotal, meta, fechamento, mensal,
                lancamentoEstrategy);

    }

}
