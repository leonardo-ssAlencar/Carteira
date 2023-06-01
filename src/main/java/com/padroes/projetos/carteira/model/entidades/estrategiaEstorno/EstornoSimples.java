package com.padroes.projetos.carteira.model.entidades.estrategiaEstorno;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

public class EstornoSimples extends EstrategiaEstorno {

    @Override
    public void calcularExtorno(Caixinha caixinha) {
        int usuarios = caixinha.getGrupo().getUsuarios().size();

        BigDecimal valorTotal = caixinha.getValorTotal();

        valorTotal = valorTotal.divide(new BigDecimal(usuarios));

        caixinha.notificar("O valor da caixinha " + caixinha.getGrupo().getNome() + " para cada um no mês "
                + LocalDate.now().getMonth() + " foi de "
                + valorTotal + " R$");

    }

}
