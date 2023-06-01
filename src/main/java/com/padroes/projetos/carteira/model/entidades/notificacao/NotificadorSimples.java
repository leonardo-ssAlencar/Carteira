package com.padroes.projetos.carteira.model.entidades.notificacao;

import com.padroes.projetos.carteira.model.entidades.Notificacoes;
import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

public class NotificadorSimples extends EstrategiaNotificacao {

    @Override
    public void notificar(String mensagem, Caixinha caixinha) {

        Notificacoes msg = new Notificacoes(mensagem);

        caixinha.getGrupo().notificar(msg);

    }

}
