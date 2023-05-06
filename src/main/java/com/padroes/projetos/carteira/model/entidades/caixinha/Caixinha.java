package com.padroes.projetos.carteira.model.entidades.caixinha;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.EstornoEstrategy;
import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.Notificador;
import com.padroes.projetos.carteira.model.entidades.excecoes.OperacaoNaoPermitidaException;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.comandos.LancamentoCommand;
import com.padroes.projetos.carteira.model.entidades.grupo.enums.CamposEnum;
import com.padroes.projetos.carteira.model.entidades.grupo.enums.TipoLancamentoEnum;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.model.entidades.lancamento.LancamentoEstrategy;

public class Caixinha {

    protected Grupo grupo;
    protected LancamentoEstrategy lancamentoEstrategy;
    protected List<Lancamento> lancamentos = new ArrayList<>();
    protected List<TipoLancamentoEnum> proibidos;
    protected List<Item> itens;
    protected Notificador notificador;
    protected EstornoEstrategy estorno;
    protected BigDecimal valorTotal;
    protected BigDecimal meta;
    protected LocalDate fechamento;
    protected boolean mensal;

    protected Caixinha(Grupo grupo, List<TipoLancamentoEnum> permitidos, List<Item> itens,
            Notificador notificador, EstornoEstrategy estorno, BigDecimal valorTotal, BigDecimal meta,
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

    public void processarLancamento(LancamentoCommand command) {

        if (proibidos != null)
            if (proibidos.contains(command.getMessage().get(CamposEnum.TIPO)))
                throw new OperacaoNaoPermitidaException("A caixinha não permite esse tipo de lancamento");

        lancamentos.add(lancamentoEstrategy.processarLancamento(this, command));

    }

}
