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

    public AdicionarItemCommand(Grupo grupo, Usuario user, List<Item> items) {
        super(grupo);
        LancamentoComItemsFactory factory = new LancamentoComItemsFactory(items);
        factory.criarLancamento(user, new BigDecimal(0), "", new AdicionarItems(items));

    }

    @Override
    public void executar() {

    }

    @Override
    public Lancamento getLancamento() {
        // TODO Auto-generated method stub
        return null;
    }

}
