package com.padroes.projetos.carteira.model.entidades.estrategiaLancamento;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.commands.LancamentoCommand;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

@Service
public class LancamentoSimples implements LancamentoEstrategy {

    @Autowired
    AplicacaoFachada fachada;

    @Override
    public Lancamento executar(Caixinha caixinha, LancamentoCommand command) {
        Lancamento lancamento = command.getLancamento();

        lancamento.setDataHoraLancamento(LocalDateTime.now());

        lancamento.setValor(lancamento.getOperacao().executarOperacao(caixinha));

        return lancamento;

    }

}
