package com.padroes.projetos.carteira.model.entidades;

import java.time.LocalDate;

public class CaixinhaBuilder {
    private Caixinha caixinha;

    public CaixinhaBuilder diaFechamento(LocalDate data) {
        return this;
    }

    public CaixinhaBuilder lancamentos(boolean proibir, TipoLancamento... lancamentos) {
        return this;
    }

    public CaixinhaBuilder devolucao(EstrategiaDevolucao estrategia) {
        return this;
    }

    public CaixinhaBuilder notificacao(EstrategiaNotificacao estrategia) {
        return this;

    }

    public Caixinha build() {
        return this.caixinha;
    }

}
