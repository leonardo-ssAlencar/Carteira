package com.padroes.projetos.carteira.model.entidades.lancamento;

import java.math.BigDecimal;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
@DiscriminatorValue("CRED")
public class Credito extends Operacao {

    @Transient
    private BigDecimal valor;

    public Credito(BigDecimal valor) {
        this.valor = valor;

    }

    @Override
    public BigDecimal executarOperacao(Caixinha caixinha) {

        caixinha.setValorTotal(caixinha.getValorTotal().add(valor));

        return valor;
    }

}
