package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.Item;

public class LancamentoComItems extends Lancamento {

    private List<Item> items;

    public LancamentoComItems(String descricao, LocalDateTime dataHora, BigDecimal valor, Operacao operacao,
            String usuario, List<Item> items) {
        super(descricao, dataHora, valor, operacao, usuario);
        this.items = items;

    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String descricao() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'descricao'");
    }

}
