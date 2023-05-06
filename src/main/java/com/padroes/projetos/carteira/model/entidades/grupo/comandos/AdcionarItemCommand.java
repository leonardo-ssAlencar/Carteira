package com.padroes.projetos.carteira.model.entidades.grupo.comandos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.grupo.enums.CamposEnum;
import static com.padroes.projetos.carteira.model.entidades.grupo.enums.CamposEnum.*;

public class AdcionarItemCommand extends LancamentoCommand {

    private Caixinha caixinha;
    private Usuario usuario;
    private List<Item> items;

    public AdcionarItemCommand(List<Item> items) {
        this.items = items;
    }

    @Override
    public void executar() {
        caixinha.processarLancamento(this);

    }

    @Override
    public Map<CamposEnum, Object> getMessage() {

        Map<CamposEnum, Object> map = new HashMap<>();

        map.put(USER, usuario.getNome());
        map.put(ITEMS, items);

        return map;
    }

}
