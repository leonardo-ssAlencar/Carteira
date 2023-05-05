package com.padroes.projetos.carteira.model.entidades;

import com.padroes.projetos.carteira.model.entidades.grupo.GrupoComponent;

public interface EstrategiaNotificacao {

    public void notificar();

    public void notificar(GrupoComponent component);

}
