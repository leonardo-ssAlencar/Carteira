package com.padroes.projetos.carteira;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.caixinha.CaixinhaBuilder;
import com.padroes.projetos.carteira.model.entidades.commands.CreditoCommand;
import com.padroes.projetos.carteira.model.entidades.commands.DebitoCommand;
import com.padroes.projetos.carteira.model.entidades.commands.LancamentoCommand;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoFachada;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.lancamento.LancamentoSemItemsFactory;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

public class TerminalApp {
        private static AplicacaoFachada fachada;
        private static ApplicationContext context;

        private static GrupoFachada grupoFachada;

        public static void main(String[] args) {
                context = new SpringApplicationBuilder(CarteiraApplication.class)
                                .web(WebApplicationType.NONE)
                                .run(args);
                fachada = context.getBean(AplicacaoFachada.class);
                grupoFachada = context.getBean(GrupoFachada.class);

                inicializarBanco();

        }

        public static void inicializarBanco() {

                Usuario user1 = new Usuario("leonardo", "", "leonardo@carteira.com", "123");
                Usuario user2 = new Usuario("marcos", "", "marcos@carteira.com", "123");
                Usuario user3 = new Usuario("maria", "", "maria@carteira.com", "123");
                Usuario user4 = new Usuario("joao", "", "joao@carteira.com", "123");
                Usuario user5 = new Usuario("jonas", "", "jonas@carteira.com", "123");
                Usuario user6 = new Usuario("julia", "", "julia@carteira.com", "123");

                // Cadastrar Usuario INICIO
                user1 = fachada.cadastrarUsuario(user1);
                // FIM
                user2 = fachada.cadastrarUsuario(user2);
                user3 = fachada.cadastrarUsuario(user3);
                user4 = fachada.cadastrarUsuario(user4);
                user5 = fachada.cadastrarUsuario(user5);
                user6 = fachada.cadastrarUsuario(user6);

                // Criar Grupo INICIO
                Grupo grupoA = grupoFachada.criarGrupo("Despesas mensais", user1);
                CaixinhaBuilder builder = new CaixinhaBuilder();
                grupoA.setCaixinha(builder.build());

                grupoA = fachada.cadastrarGrupo(grupoA);

                fachada.cadastrarParticipante(grupoA, (Grupo) user1.getParente());

                // FIM

                // Cadastrar Participante INICIO
                fachada.cadastrarParticipante(user2, grupoA);
                // FIM
                fachada.cadastrarParticipante(user3, grupoA);
                fachada.cadastrarParticipante(user4, grupoA);
                fachada.cadastrarParticipante(user5, grupoA);
                fachada.cadastrarParticipante(user6, grupoA);

                LancamentoCommand command = new DebitoCommand(grupoA, BigDecimal.valueOf(10.00), user1, "Agua",
                                new LancamentoSemItemsFactory());

                command.executar();

                LancamentoCommand command2 = new CreditoCommand(grupoA, BigDecimal.valueOf(5.00), user4,
                                "minha parte da agua",
                                new LancamentoSemItemsFactory());

                command2.executar();

                LancamentoCommand command3 = new DebitoCommand(grupoA, BigDecimal.valueOf(50.00), user6, "Compras",
                                new LancamentoSemItemsFactory());

                command3.executar();

                fachada.salvarLancamento(command.getLancamento());
                fachada.salvarLancamento(command2.getLancamento());
                fachada.salvarLancamento(command3.getLancamento());

                Item item1 = new Item("coxão mole", 0, 1, "2kg de coxao mole", BigDecimal.valueOf(51.00));
                Item item2 = new Item("Cerveja latão", 0, 10, "Caixa com 12 latoes", BigDecimal.valueOf(110.00));
                Item item3 = new Item("Refrigerante", 0, 10, "Refrigerante de 2L marcas variaveis",
                                BigDecimal.valueOf(51.00));

                builder.items(Arrays.asList(item1, item2, item3));
                builder.fechamento(LocalDate.of(2023, 6, 10));
                builder.eMensal(false);
                Grupo grupoB = grupoFachada.criarGrupo("Churrasco final de semana", user1);
                grupoB.setCaixinha(builder.build());

                grupoB = fachada.cadastrarGrupo(grupoB);
                fachada.cadastrarParticipante(grupoB, (Grupo) user1.getParente());

                fachada.cadastrarParticipante(user2, grupoB);
                fachada.cadastrarParticipante(user3, grupoB);
                fachada.cadastrarParticipante(user4, grupoB);

        }

}
