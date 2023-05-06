package com.padroes.projetos.carteira.model.entidades.grupo.comandos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.padroes.projetos.carteira.model.entidades.grupo.enums.CamposEnum.*;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.grupo.enums.CamposEnum;
import com.padroes.projetos.carteira.model.entidades.grupo.enums.TipoLancamentoEnum;

public class DebitoLancamentoCommand extends LancamentoCommand {

    private Caixinha caixinha;
    private Usuario user;
    private BigDecimal valor;
    private String mensagem;
    private List<Item> items;

    public DebitoLancamentoCommand(Usuario user, Caixinha caixinha, BigDecimal valor, String msg, List<Item> items) {
        this.caixinha = caixinha;
        this.user = user;
        this.valor = valor;
        this.mensagem = msg;
        this.items = items;

    }

    @Override
    public void executar() {
        caixinha.processarLancamento(this);

    }

    @Override
    public Map<CamposEnum, Object> getMessage() {
        Map<CamposEnum, Object> map = new HashMap<>();
        map.put(USER, user.getNome());
        map.put(VALOR, valor);
        map.put(MSG, mensagem);

        if (items != null) {
            map.put(ITEMS, items);
            map.put(TIPO, TipoLancamentoEnum.DEBITO_ITEMS);
            return map;

        }

        map.put(TIPO, TipoLancamentoEnum.DEBITO);
        return map;
    }

}
