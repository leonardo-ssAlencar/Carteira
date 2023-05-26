package com.padroes.projetos.carteira.model.entidades.caixinha;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.commands.LancamentoCommand;
import com.padroes.projetos.carteira.model.entidades.estrategiaEstorno.EstrategiaEstorno;
import com.padroes.projetos.carteira.model.entidades.estrategiaLancamento.LancamentoEstrategy;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.model.entidades.notificacao.EstrategiaNotificacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Caixinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    protected Grupo grupo;

    @ManyToOne
    protected LancamentoEstrategy lancamentoEst;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "caixinha_id")
    protected List<Lancamento> lancamentos = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "caixinha_id")
    protected List<Item> itens = new ArrayList<>();

    @ManyToOne
    protected EstrategiaNotificacao notificador;

    @ManyToOne
    protected EstrategiaEstorno estornoEst;

    protected BigDecimal valorTotal;
    protected BigDecimal meta;
    protected LocalDate fechamento;
    protected boolean mensal;

    protected Caixinha(Grupo grupo, List<Item> itens,
            EstrategiaNotificacao notificador, EstrategiaEstorno estorno, BigDecimal valorTotal, BigDecimal meta,
            LocalDate fechamento, boolean mensal, LancamentoEstrategy lancamentoEstrategy) {
        this.grupo = grupo;

        this.itens = itens;
        this.notificador = notificador;
        this.estornoEst = estorno;
        this.valorTotal = valorTotal;
        this.meta = meta;
        this.fechamento = fechamento;
        this.mensal = mensal;
        this.lancamentoEst = lancamentoEstrategy;
    }

    public Caixinha() {

    }

    public void executarLancamento(LancamentoCommand command) {
        Lancamento novoLancamento = lancamentoEst.executar(this, command);
        lancamentos.add(novoLancamento);
    }

    public void notificar(String mensagem) {
        this.notificador.notificar(mensagem, this);

    }

    public void realizarEstorno() {
        this.estornoEst.calcularExtorno(this);
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
        return lancamentoEst;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
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
        return estornoEst;
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
