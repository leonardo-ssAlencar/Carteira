package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
@DiscriminatorValue("DEB")
public class Debito extends Operacao {

    @Transient
    private BigDecimal valor;

    public Debito(BigDecimal valor) {
        this.valor = valor;

    }

    @Override
    public BigDecimal executarOperacao(Caixinha caixinha) {

        caixinha.setValorTotal(caixinha.getValorTotal().subtract(valor));

        return valor.negate();

    }

}
