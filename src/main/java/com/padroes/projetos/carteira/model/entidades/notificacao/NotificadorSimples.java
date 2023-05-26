package com.padroes.projetos.carteira.model.entidades.notificacao;

import com.padroes.projetos.carteira.model.entidades.Mensagens;
import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

import jakarta.persistence.Entity;

@Entity
public class NotificadorSimples extends EstrategiaNotificacao {

    @Override
    public void notificar(String mensagem, Caixinha caixinha) {

        Mensagens msg = new Mensagens(mensagem);

        caixinha.getGrupo().notificar(msg);

    }

}
