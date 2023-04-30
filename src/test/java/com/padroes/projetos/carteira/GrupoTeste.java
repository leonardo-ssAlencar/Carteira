package com.padroes.projetos.carteira;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.padroes.projetos.carteira.model.entidades.Grupo;
import com.padroes.projetos.carteira.model.entidades.GrupoComponent;
import com.padroes.projetos.carteira.model.entidades.Usuario;

@SpringBootTest
public class GrupoTeste {

    private Usuario u1 = new Usuario("leonardo", "75 8888-8888", "Leonardo@pikles.com", "020022112");

    private Grupo groupUser1 = new Grupo(u1);

    @Test
    public void grupoUsuario() {
        // Testa se o grupo raiz foi criado com o usuario
        assertEquals(u1.getNome(), groupUser1.getNome(), "O grupo não foi criado com o nome do usuario");

        // Testa se o grupo é um grupo raiz
        assertEquals(GrupoComponent.root, groupUser1.getParente().get(), "O grupo não foi criado como raiz");
        // Testar se vai lançar uma exceção ao tentar incluir outro usuario a um grupo
        // root
        GrupoComponent novoUsuario = new Usuario("Jose", "11111111111", "jose@carteira.com", "75988818182");

        // Testa se a classe esta colocando mais de um usuario no grupo root
        assertThrows(RuntimeException.class, () -> groupUser1.setParticipante(novoUsuario),
                "É invalido adicionar mais de um usuario a um grupo root");

        assertEquals(u1, groupUser1.getDono(), "O usuario não passado para o grupo não foi transformado em dono");

    }

    @Test
    public void eGrupo() {
        assertInstanceOf(Grupo.class, groupUser1);

    }

    @Test
    public void eRoot() {
        assertEquals(GrupoComponent.root, groupUser1.getParente().get());

    }

    @Test
    public void usuarioNome() {
        assertEquals("raiz", groupUser1.getParente().get().getNome());

    }

    @Test
    public void criarGrupo() {
        GrupoComponent g1 = new Grupo("familia", groupUser1);

        assertEquals("familia", g1.getNome());
        assertEquals("leonardo", g1.getParente().get().getNome());
        assertInstanceOf(Grupo.class, g1.getParente().get());
        assertEquals(groupUser1.getAdministradores(), g1.getFilhos());
    }

    @Test
    public void popularGrupo() {
        Grupo g1 = new Grupo("familia", groupUser1);

        List<GrupoComponent> lista = Arrays.asList(
                new Usuario("Joao", "55555", "joao@pikles.com", "123"),
                new Usuario("Marcos", "55555", "marcos@pikles.com", "123"),
                new Usuario("Maria", "55555", "maria@pikles.com", "123"),
                new Grupo("Aniversario", g1));

        g1.setParticipante(lista);

        assertEquals(g1.getFilhos().size() + 1, g1.getFilhos().size());

    }

}
