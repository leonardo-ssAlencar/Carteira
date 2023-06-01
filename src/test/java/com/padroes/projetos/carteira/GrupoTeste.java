package com.padroes.projetos.carteira;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.padroes.projetos.carteira.model.entidades.excecoes.OperacaoNaoPermitidaException;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoComponent;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoFachada;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;

@SpringBootTest
public class GrupoTeste {

    GrupoFachada grupoInterface = new GrupoFachada();

    Usuario user1;
    Grupo grupo;

    @BeforeEach
    public void inicializarGrupoRoot() {
        user1 = new Usuario("leonardo", "88888888", "leonardo@carteira.com", "ull");
        grupo = grupoInterface.criarGrupoUsuario(user1);
    }

    @Test
    public void criarGrupoUsuario() {
        // grupo = grupoInterface.criarGrupoUsuario(user1);

        assertEquals(user1.getNome(), grupo.getNome(), "O nome do grupo é diferente do nome do usuario");
        assertEquals(null, grupo.getParente(), "O grupo não tem o grupoRaiz como pai");
        assertEquals(user1, grupo.getDono(), "O grupo não recebeu como o dono o Usuario passado!");

        Usuario usuario = new Usuario("aaa", "null", "null", "ull");

        assertThrows(OperacaoNaoPermitidaException.class, () -> grupo.setParticipantes(usuario),
                "usuario foi adicionado a um grupo root");

    }

    @Test
    public void usuarioTestes() {
        String nome = "leonardo";
        String emai = nome + "@carteira.com";
        String telefone = "(WW)9XXXX-YYYY";
        String senha = "1234";

        Usuario usuario = new Usuario(nome, telefone, emai, senha);

        Grupo grupoTeste = grupoInterface.criarGrupoUsuario(usuario);

        assertNotNull(usuario.getParente(), "O parente não foi adicionado");
        assertEquals(grupoTeste, usuario.getParente(), "O grupo pai é diferente do esperado");

        assertEquals(nome, usuario.getNome());
        assertEquals(emai, usuario.getEmail());
        assertEquals(telefone, usuario.getTelefone());
        assertEquals(senha, usuario.getSenha());

    }

    @Test
    public void criarGrupo() {
        String nomeGrupo = "familia";
        Grupo grupo1 = grupoInterface.criarGrupo(nomeGrupo, user1);

        long x = 0;

        grupo1.setId(x++);

        assertNotNull(grupo1, "O grupo não foi criado");

        assertEquals(nomeGrupo, grupo1.getNome(), "O nome do grupo é diferente do esperado");
        assertEquals(grupo, grupo1.getParente(), "O grupo não tem o pai esperado");
        assertEquals(user1, grupo1.getDono(), "O grupo não recebeu como o dono o Usuario passado!");

        List<GrupoComponent> participantes = Arrays.asList(
                new Usuario("joao", "1999999999", "joao@carteira.com", "nomeGrupo"),
                new Usuario("marcos", "1999999999", "marcos@carteira.com", "nomeGrupo"),
                new Usuario("maria", "1999999999", "maria@carteira.com", "nomeGrupo"),
                new Usuario("jonatan", "1999999999", "jonatan@carteira.com", "nomeGrupo"));

        for (GrupoComponent component : participantes) {
            component.setId(x++);
            assertTrue(() -> grupo1.setParticipantes(component), "Não adicionou os usuarios");

        }

        Usuario novoAdmin = new Usuario("admin2", "(WW)9XXXX-YYYY", "admin2@carteira.com", "nomeGrupo");
        novoAdmin.setId(x++);

        assertTrue(() -> grupo1.setParticipantes(novoAdmin), "Nao adicionou o participante");

        // Testa o administrador

        assertEquals(1, grupo1.getAdministradores().size());
        assertTrue(grupo1.getAdministradores().contains(user1), "O usuario não adicionado como administrador");

        // Testa se os participante foram adicionado

        assertEquals(participantes.size() + 2, grupo1.getParticipantes().size());
        List<GrupoComponent> g1Participantes = grupo1.getParticipantes();
        assertTrue(g1Participantes.containsAll(g1Participantes), "Não foram adicionados os participantes do grupo");

        assertFalse(() -> grupo1.setParticipantes(novoAdmin),
                "Um mesmo participante não pode ser adicionado duas vezes");

        Usuario naoVaiTaNoGrup = new Usuario("naoVai", "775555555", "naoVai@carteira.com", "1223");
        naoVaiTaNoGrup.setId(x++);

        assertThrows(OperacaoNaoPermitidaException.class, () -> grupo1.tornarAdmin(naoVaiTaNoGrup),
                "Usuario que não está no grupo não pode ser administrador ");

        assertFalse(() -> grupo1.setParticipantes(novoAdmin), "Foi adicionado dois");
        assertDoesNotThrow(() -> grupo1.tornarAdmin(novoAdmin), "Exceção não prevista");

        assertEquals(2, grupo1.getAdministradores().size(), "Teve algum problema ao adicionar");
        assertTrue(grupo1.getAdministradores().contains(novoAdmin), "O usuario não foi adicionado como administrador");

    }

}
