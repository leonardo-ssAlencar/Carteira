package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
@DiscriminatorValue("ADD-ITEM")
public class AdicionarItems extends Operacao {

    @Transient
    private List<Item> items;

    public AdicionarItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public BigDecimal executarOperacao(Caixinha caixinha) {

        List<Item> caixinhaItems = caixinha.getItens();
        List<Item> novosItems = new ArrayList<>();

        for (Item item : this.items) {
            if (caixinhaItems.contains(item)) {
                Optional<Item> optItem = caixinhaItems.stream().filter(x -> x.equals(item)).findFirst();
                optItem.get().setQuantidade(item.getQuantidade());
                continue;

            }

            novosItems.add(item);
        }

        caixinha.addItems(novosItems);

        return new BigDecimal(0);
    }

}
