package com.padroes.projetos.carteira;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.padroes.projetos.carteira.model.entidades.Notificacoes;
import com.padroes.projetos.carteira.model.entidades.caixinha.CaixinhaBuilder;
import com.padroes.projetos.carteira.model.entidades.commands.CreditoCommand;
import com.padroes.projetos.carteira.model.entidades.commands.DebitoCommand;
import com.padroes.projetos.carteira.model.entidades.commands.LancamentoCommand;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoFachada;
import com.padroes.projetos.carteira.model.entidades.grupo.Participante;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.lancamento.LancamentoSemItemsFactory;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

public class TerminalApp {
    private static AplicacaoFachada fachada;
    private static ApplicationContext context;
    private static GrupoFachada gFachada;
    private static Usuario userLogado;
    private static Grupo contextoGrupo;

    public static void main(String[] args) {
        context = new SpringApplicationBuilder(CarteiraApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        fachada = context.getBean(AplicacaoFachada.class);
        gFachada = context.getBean(GrupoFachada.class);

        cadastrarUsuarios();

        fazerLogin();

        criarNovoGrupo();

        adicionarParticipante();

        executarLancamento();

        executarNotificacao();

    }

    public static void cadastrarUsuarios() {

        Usuario user = new Usuario("leonardo", "", "leonardo@carteira.com", "123");
        Usuario user2 = new Usuario("marcos", "", "marcos@carteira.com", "123");
        Usuario user3 = new Usuario("maria", "", "maria@carteira.com", "123");
        Usuario user4 = new Usuario("joao", "", "joao@carteira.com", "123");
        Usuario user5 = new Usuario("jonas", "", "jonas@carteira.com", "123");
        Usuario user6 = new Usuario("julia", "", "julia@carteira.com", "123");

        user = fachada.cadastrarUsuario(user);
        user2 = fachada.cadastrarUsuario(user2);
        user3 = fachada.cadastrarUsuario(user3);
        user4 = fachada.cadastrarUsuario(user4);
        user5 = fachada.cadastrarUsuario(user5);
        user6 = fachada.cadastrarUsuario(user6);

    }

    public static void fazerLogin() {

        Optional<Usuario> usrOpt = fachada.validarUsuario("leonardo@carteira.com", "123");

        if (usrOpt.isPresent()) {
            userLogado = usrOpt.get();
            return;
        }

        System.exit(0);

    }

    public static void criarNovoGrupo() {

        Grupo grupo = gFachada.criarGrupo("Despesas mensais", userLogado);

        CaixinhaBuilder builder = new CaixinhaBuilder();

        grupo.setCaixinha(builder.build());

        contextoGrupo = fachada.cadastrarGrupo(grupo);

    }

    public static void adicionarParticipante() {

        Participante participante = fachada.buscarUsuario("jonas@carteira.com");
        // Grupo grupo = fachada.buscarGrupo(13L);

        fachada.cadastrarParticipante(participante, contextoGrupo);

    }

    public static void executarLancamento() {

        LancamentoCommand command = new DebitoCommand(contextoGrupo, BigDecimal.valueOf(10), userLogado, "Agua",
                new LancamentoSemItemsFactory());

        command.executar();

        LancamentoCommand command2 = new CreditoCommand(contextoGrupo, BigDecimal.valueOf(10), userLogado, "Agua",
                new LancamentoSemItemsFactory());

        command2.executar();

        fachada.salvarLancamento(command.getLancamento());
        fachada.salvarLancamento(command2.getLancamento());

    }

    public static void executarNotificacao() {

        List<Notificacoes> notificacoes = contextoGrupo.getCaixinha()
                .notificar("Ola a todos bem vindo ao meu grupo. Qualquer duvidas é só perguntar");

        fachada.salvarNotificacoes(notificacoes);

    }

}
