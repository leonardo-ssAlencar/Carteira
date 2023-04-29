package com.padroes.projetos.carteira.model.entidades;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface GrupoComponent {

    public static GrupoComponent root = new GrupoComponent() {
        @Override
        public Optional<GrupoComponent> getParente() {
            return Optional.ofNullable(null);
        }

        @Override
        public String getNome() {
            return "raiz";

        }

        @Override
        public List<GrupoComponent> getFilhos() {
            return Arrays.asList();
        }

    };

    public List<GrupoComponent> getFilhos();

    public Optional<GrupoComponent> getParente();

    public String getNome();

}
