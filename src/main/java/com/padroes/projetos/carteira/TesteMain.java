package com.padroes.projetos.carteira;

import java.util.Arrays;

import com.padroes.projetos.carteira.model.entidades.Grupo;
import com.padroes.projetos.carteira.model.entidades.GrupoComponent;
import com.padroes.projetos.carteira.model.entidades.Usuario;

public class TesteMain {
    public static void main(String[] args) {

        // Cria o usuario
        Usuario u1 = new Usuario("leonardo", "75 8888-8888", "Leonardo@pikles.com", "020022112");
        // Adiciona em um grupo root
        Usuario u2 = new Usuario("joao", "75999999999", "joao@carteira.com",
                "051110454");
        Usuario u3 = new Usuario("marcos", "75999999999", "marcos@carteira.com",
                "051110454");

        Grupo groupUser1 = new Grupo(u1);

        Grupo grupo1 = new Grupo("Familia", groupUser1);
        groupUser1.setParticipante(grupo1);

        grupo1.setParticipante(Arrays.asList(u2, u3));

        // for (GrupoComponent gc : groupUser1.getFilhos()) {
        // System.out.println("Nome: " + gc.getNome());
        // }

        for (GrupoComponent gc : grupo1.getFilhos()) {
            System.out.println("Nome: " + gc.getNome());
        }
        for (GrupoComponent gc : grupo1.getAdministradores()) {
            System.out.println("Admin: " + gc.getNome());

        }

    }

}
