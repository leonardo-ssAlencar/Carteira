package com.padroes.projetos.carteira.model.entidades.grupo.comandos;

import static com.padroes.projetos.carteira.model.entidades.grupo.enums.CamposEnum.MSG;
import static com.padroes.projetos.carteira.model.entidades.grupo.enums.CamposEnum.USER;
import static com.padroes.projetos.carteira.model.entidades.grupo.enums.CamposEnum.VALOR;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.padroes.projetos.carteira.model.entidades.grupo.enums.CamposEnum;

public class CreditoLancamentoCommand extends LancamentoCommand {

    private BigDecimal valor;
    private String msg;

    public CreditoLancamentoCommand(BigDecimal valor, String msg) {
        this.valor = valor;
        this.msg = msg;
    }

    @Override
    public void executar() {
        caixinha.processarLancamento(this);

    }

    @Override
    public Map<CamposEnum, Object> getMessage() {
        Map<CamposEnum, Object> map = new HashMap<>();

        map.put(USER, usuario.getNome());
        map.put(VALOR, valor);
        map.put(MSG, msg);

        return map;

    }

}
