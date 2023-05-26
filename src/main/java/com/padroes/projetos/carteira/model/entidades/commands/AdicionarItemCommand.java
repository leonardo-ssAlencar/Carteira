package com.padroes.projetos.carteira.model.entidades.commands;

import java.math.BigDecimal;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.lancamento.AdicionarItems;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.model.entidades.lancamento.LancamentoComItemsFactory;

public class AdicionarItemCommand extends LancamentoCommand {

    private Lancamento lancamento;

    public AdicionarItemCommand(Grupo grupo, Usuario user, List<Item> items) {
        super(grupo);
        LancamentoComItemsFactory factory = new LancamentoComItemsFactory(items);
        this.lancamento = factory.criarLancamento(user, new BigDecimal(0), "", new AdicionarItems(items));

    }

    @Override
    public void executar() {
        getGrupo().getCaixinha().executarLancamento(this);

    }

    @Override
    public Lancamento getLancamento() {
        return this.lancamento;
    }

}