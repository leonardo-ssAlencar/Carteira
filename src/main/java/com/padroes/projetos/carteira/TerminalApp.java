package com.padroes.projetos.carteira;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.caixinha.CaixinhaBuilder;
import com.padroes.projetos.carteira.model.entidades.commands.DebitoCommand;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoFachada;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.model.entidades.lancamento.LancamentoSemItemsFactory;
import com.padroes.projetos.carteira.repository.RepositorioCaixinha;
import com.padroes.projetos.carteira.repository.RepositorioGrupo;
import com.padroes.projetos.carteira.repository.RepositorioUsuario;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

public class TerminalApp {
    private static AplicacaoFachada fachada;

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(CarteiraApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        fachada = context.getBean(AplicacaoFachada.class);
        GrupoFachada gFachada = context.getBean(GrupoFachada.class);
        RepositorioUsuario userRep = context.getBean(RepositorioUsuario.class);
        RepositorioGrupo grupoRep = context.getBean(RepositorioGrupo.class);
        RepositorioCaixinha caixRep = context.getBean(RepositorioCaixinha.class);

        Usuario user = new Usuario("leonado", "", "leonardo@carteira.com", "123");
        Usuario user2 = new Usuario("marcos", "", "marcos@carteira.com", "123");
        Usuario user3 = new Usuario("maria", "", "maria@carteira.com", "123");
        Usuario user4 = new Usuario("joao", "", "joao@carteira.com", "123");
        Usuario user5 = new Usuario("jonas", "", "jonas@carteira.com", "123");
        Usuario user6 = new Usuario("julia", "", "julia@carteira.com", "123");

        fachada.cadastrarUsuario(user);
        fachada.cadastrarUsuario(user2);
        fachada.cadastrarUsuario(user3);
        fachada.cadastrarUsuario(user4);
        fachada.cadastrarUsuario(user5);
        fachada.cadastrarUsuario(user6);

        Grupo grupo = gFachada.criarGrupo("despesas mensais", user);

        Caixinha caixinha;
        CaixinhaBuilder builder = new CaixinhaBuilder().meta(new BigDecimal(1000.00));

        caixinha = builder.build();

        grupo.setCaixinha(caixinha);

        userRep.findAll().stream().forEach(System.out::println);

        grupo.setParticipantes(user2);
        grupo.setParticipantes(user3);
        grupo.setParticipantes(user4);
        grupo.setParticipantes(user5);
        grupo.setParticipantes(user6);

        caixRep.save(caixinha);
        grupo = grupoRep.save(grupo);

        DebitoCommand command = new DebitoCommand(grupo, new BigDecimal(10.00), user6, "agua mineral",
                new LancamentoSemItemsFactory());

        command.executar();

        for (Lancamento lanc : grupo.getCaixinha().getLancamentos()) {

            System.out.println(lanc);

        }

    }

}
