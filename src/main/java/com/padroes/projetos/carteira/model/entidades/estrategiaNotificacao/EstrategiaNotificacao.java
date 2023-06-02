package com.padroes.projetos.carteira.model.entidades.estrategiaNotificacao;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

public interface EstrategiaNotificacao {

    public abstract void notificar(String mensagem, Caixinha caixinha);

}
