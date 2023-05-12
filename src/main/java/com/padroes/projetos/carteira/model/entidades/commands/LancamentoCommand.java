package com.padroes.projetos.carteira.model.entidades.commands;

import java.math.BigDecimal;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.lancamento.Operacao;

public abstract class LancamentoCommand {

    protected Usuario user;
    protected Caixinha caixinha;
    protected BigDecimal valor;
    protected List<Item> items;
    protected String mensagem;
    protected Operacao operacao;

    public LancamentoCommand(Usuario user, Caixinha caixinha, BigDecimal valor) {
        this.user = user;
        this.caixinha = caixinha;
        this.valor = valor;
    }

    public abstract void executar();

    public Usuario getUser() {
        return user;
    }

    public Caixinha getCaixinha() {
        return caixinha;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public LancamentoCommand mensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;

    }

    public LancamentoCommand items(List<Item> items) {
        this.items = items;
        return this;
    }

}
