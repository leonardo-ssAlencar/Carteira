package com.padroes.projetos.carteira.model.entidades.estrategiaNotificacao;

import com.padroes.projetos.carteira.model.entidades.Notificacoes;
import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

public class NotificadorSimples implements EstrategiaNotificacao {

    @Override
    public void notificar(String mensagem, Caixinha caixinha) {

        Notificacoes msg = new Notificacoes(mensagem);

        caixinha.getGrupo().notificar(msg);

    }

}
