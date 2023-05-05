package com.padroes.projetos.carteira;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.padroes.projetos.carteira.model.entidades.grupo.GrupoFachada;

@SpringBootTest
class CarteiraApplicationTests {

	@Autowired
	GrupoFachada gi;

	@Test
	void contextLoads() {
		assertNotNull(gi);

	}

}
