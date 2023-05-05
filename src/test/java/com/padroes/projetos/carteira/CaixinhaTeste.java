package com.padroes.projetos.carteira;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.caixinha.CaixinhaBuilder;

@TestComponent
public class CaixinhaTeste {

    @Test
    public void caixinhaTeste() {

        Caixinha caixinha = CaixinhaBuilder.builder()
                .build();

        assertNotNull(caixinha, "A caixinha tá nula");

    }

}
