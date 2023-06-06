package com.padroes.projetos.carteira.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.repository.RepositorioCaixinha;
import com.padroes.projetos.carteira.repository.RepositorioGrupo;

@Component
@EnableScheduling
public class Agendamentos {

    // private final String timeZone = "America/Sao_Paulo";

    @Autowired
    RepositorioCaixinha caixaRepo;
    @Autowired
    AplicacaoFachada fachada;
    @Autowired
    RepositorioGrupo grupoRepo;

    // @Scheduled(cron = "0 0 0", zone = timeZone)
    // @Scheduled(fixedDelay = 1000 * 10)
    public void fechamentoCaixinha() {

        List<Caixinha> lista = caixaRepo.caixinhasFechadas();

        for (Caixinha caixinha : lista) {

            caixinha.realizarEstorno();

            if (caixinha.isMensal()) {
                caixinha.setFechamento(caixinha.getFechamento().plusMonths(1));

            }

            caixaRepo.save(caixinha);

            fachada.salvarNotificacoes(caixinha.getUltimasNotificacoes());

        }

    }

}
