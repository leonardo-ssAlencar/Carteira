package com.padroes.projetos.carteira.model.entidades.grupo.comandos;

import java.util.Map;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.grupo.enums.CamposEnum;

public abstract class LancamentoCommand {

    protected Caixinha caixinha;
    protected Usuario usuario;

    public abstract void executar();

    public abstract Map<CamposEnum, Object> getMessage();

    public void setCaixinha(Caixinha caixinha) {
        this.caixinha = caixinha;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
