package com.padroes.projetos.carteira;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoInterface;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;

@SpringBootTest
public class GrupoTeste {

    @Autowired // Injeta a dependencia, o mesmo que fazer new GrupoInterface();
    GrupoInterface grupoInterface;

    Usuario user1;
    Grupo grupo;

    @BeforeEach
    public void inicializarGrupoRoot() {
        user1 = new Usuario("leonardo", "88888888", "leonardo@carteira.com", "ull");
        grupo = grupoInterface.criarGrupoUsuario(user1);
    }

    @Test
    public void criarGrupoUsuario() {
        user1 = new Usuario("leonardo", "88888888", "leonardo@carteira.com", "ull");

        grupo = grupoInterface.criarGrupoUsuario(user1);

        assertEquals(user1.getNome(), grupo.getNome(), "O nome do grupo é diferente do nome do usuario");
        assertEquals(GrupoInterface.grupoRaiz, grupo.getParente().get(), "O grupo não tem o grupoRaiz como pai");
        assertEquals(user1, grupo.getDono(), "O grupo não recebeu como o dono o Usuario passado!");
        assertThrows(UnsupportedOperationException.class, () -> grupo.getParticipantes(),
                "A variavel participante foi inicializada");

    }

    @Test
    public void criarGrupo() {
        String nomeGrupo = "familia";
        Grupo grupo1 = grupoInterface.criarGrupo(nomeGrupo, grupo, user1);

        assertNotNull(grupo1, "O grupo não foi criado");

        assertEquals(nomeGrupo, grupo1.getNome(), "O nome do grupo é diferente do esperado");
        assertEquals(grupo, grupo1.getParente().get(), "O grupo não tem o pai esperado");
        assertEquals(user1, grupo1.getDono(), "O grupo não recebeu como o dono o Usuario passado!");

    }

}
