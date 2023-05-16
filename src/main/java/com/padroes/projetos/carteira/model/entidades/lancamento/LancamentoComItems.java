package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;

public class LancamentoComItems extends Lancamento {

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
