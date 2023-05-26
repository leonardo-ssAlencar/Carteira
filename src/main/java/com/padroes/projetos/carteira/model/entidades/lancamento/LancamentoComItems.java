package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("COM_ITEM")
public class LancamentoComItems extends Lancamento {

    @OneToMany
    protected List<Item> items;

    public LancamentoComItems(Usuario usuario, String mensagem, BigDecimal valor, Operacao operacao,
            LocalDateTime dataHoraLancamento, List<Item> items) {
        super(usuario, mensagem, operacao, dataHoraLancamento);
        this.items = items;
    }

    public LancamentoComItems(List<Item> items) {
        this.items = items;
    }

}
