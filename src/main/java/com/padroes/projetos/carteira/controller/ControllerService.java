package com.padroes.projetos.carteira.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ControllerService {

    public BigDecimal calcularValorTotal(List<BigDecimal> lancamentos) {
        BigDecimal valor = new BigDecimal(0);

        if (lancamentos != null) {
            valor = lancamentos.stream().reduce((x, y) -> x.add(y)).orElse(valor);
        }

        return valor;

    }

}
