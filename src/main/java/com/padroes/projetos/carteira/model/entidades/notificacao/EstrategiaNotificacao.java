package com.padroes.projetos.carteira.model.entidades.notificacao;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

public interface EstrategiaNotificacao {

    public void notificar(String mensagem, Caixinha caixinha);

}
