package com.padroes.projetos.carteira;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.caixinha.CaixinhaBuilder;
import com.padroes.projetos.carteira.model.entidades.commands.DebitoCommand;
import com.padroes.projetos.carteira.model.entidades.commands.LancamentoCommand;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoFachada;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.model.entidades.lancamento.LancamentoFactory;
import com.padroes.projetos.carteira.model.entidades.lancamento.LancamentoSemItemsFactory;

@TestComponent
public class CaixinhaTeste {

    GrupoFachada fachada = new GrupoFachada();

    Usuario user;
    Grupo grupo;
    Caixinha caixinha;
    long x = 0;

    @BeforeEach
    public void inicializar() {
        user = new Usuario("leonardo", "19999999", "leonardo@mail.com", "1233");
        user.setId(x++);
        grupo = fachada.criarGrupoUsuario(user);

        CaixinhaBuilder builder = new CaixinhaBuilder();

        caixinha = builder.build(grupo);

        grupo.setCaixinha(caixinha);

    }

    @Test
    public void checarCaixinha() {
        assertEquals(LocalDate.of(2023, 05, 31), caixinha.getFechamento());
        assertFalse(caixinha.isMensal());
        assertEquals(new BigDecimal(0), caixinha.getValorTotal());
        assertNull(caixinha.getItens());
        assertNull(caixinha.getMeta());

    }

    @Test
    public void fazerLancamento() {
        BigDecimal valor = new BigDecimal(100);

        LancamentoFactory factory = new LancamentoSemItemsFactory();

        assertNotNull(factory);

        LancamentoCommand command = new DebitoCommand(grupo, valor, user, "Energia", factory);

        assertNotNull(command.getLancamento());

        command.executar();

        assertEquals(valor.negate(), caixinha.getValorTotal());

        for (Lancamento l : caixinha.getLancamentos()) {
            assertEquals(command.getLancamento(), l);

        }

    }
}
