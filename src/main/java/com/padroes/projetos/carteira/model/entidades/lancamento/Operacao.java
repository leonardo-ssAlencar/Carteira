package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

public interface Operacao {

    public BigDecimal executarOperacao(Caixinha caixinha);

}