package com.padroes.projetos.carteira;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.padroes.projetos.carteira.model.entidades.Item;
import com.padroes.projetos.carteira.model.entidades.caixinha.CaixinhaBuilder;
import com.padroes.projetos.carteira.model.entidades.commands.CreditoCommand;
import com.padroes.projetos.carteira.model.entidades.commands.DebitoCommand;
import com.padroes.projetos.carteira.model.entidades.commands.LancamentoCommand;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoComponent;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoFachada;
import com.padroes.projetos.carteira.model.entidades.grupo.Participante;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.model.entidades.lancamento.LancamentoSemItemsFactory;
import com.padroes.projetos.carteira.model.enums.OperacoesEnum;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

public class TerminalApp {
    private static AplicacaoFachada fachada;
    private static ApplicationContext context;
    private static Scanner sc;

    private static GrupoFachada grupoFachada;
    private static Usuario userLogado;
    private static Grupo grupoUser;

    public static void limparBuffer(Scanner cScanner) {
        if (cScanner.hasNextLine()) {
            cScanner.nextLine();
        }

    }

    public static void main(String[] args) {
        context = new SpringApplicationBuilder(CarteiraApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        fachada = context.getBean(AplicacaoFachada.class);
        grupoFachada = context.getBean(GrupoFachada.class);

        sc = new Scanner(System.in);

        inicializarBanco();
        short opcao = 0;
        boolean loop = true;

        do {

            System.out.println("Digite:\n1 - Login\n2 - Cadastrar\n -- Escolha: ");
            opcao = sc.nextShort();

            limparBuffer(sc);

            switch (opcao) {

                case 1:
                    loginTela();
                    break;

                case 2:
                    cadastroTela();
                    break;

                default:
                    loop = false;
                    break;

            }

        } while (loop);

    }

    public static void loginTela() {

        System.out.println("---------- LOGIN: ----------\n\n");
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        // Login do Usuario INICIO
        Optional<Usuario> user = fachada.validarUsuario(email, senha);

        if (user.isPresent()) {
            userLogado = user.get();
            grupoUser = (Grupo) userLogado.getParente();
            telaUsuario();
            return;
        }
        // FIM

        System.out.println("Usuario não existe ou senha incorreta");
    }

    public static void cadastroTela() {

        System.out.println("---------- CADASTRO: ----------\n\n");
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.println("Telefone: ");
        String tel = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        fachada.cadastrarUsuario(new Usuario(nome, tel, email, senha));

    }

    // Pode pegar esse metodo para calcular o saldo tanto na tela do usuario quanto
    // na do grupo
    public static BigDecimal calcularSaldo(GrupoComponent grupoComponent) {
        List<BigDecimal> lancamentos = fachada.valorLancamentos(userLogado);
        BigDecimal valor = new BigDecimal(0);

        if (lancamentos != null) {
            valor = lancamentos.stream().reduce((x, y) -> x.add(y)).orElse(valor);
        }

        return valor;

    }

    public static void telaUsuario() {
        short opcao = 0;
        BigDecimal valor = calcularSaldo(userLogado);

        do {

            System.out.println("Ola " + userLogado.getNome() + " ------- " + "Saldo: " + valor);
            System.out.print("Digite:\n1 - Listar grupos\n2 - Cadastrar Grupo\n3 - ver seus lancamentos\nOpcao: ");
            opcao = sc.nextShort();

            limparBuffer(sc);

            switch (opcao) {
                case 1:
                    listaGruposTela();

                    break;
                case 2:
                    cadastroGrupoTela();
                    break;

                case 3:
                    listarLancamentosTela(userLogado);
                    break;

                default:
                    break;
            }

        } while (opcao != 0);

    }

    public static void cadastroGrupoTela() {

        System.out.println("CADASTRAR GRUPO");

        System.out.println("Nome: ");
        String nome = sc.nextLine();

        Grupo grupo = grupoFachada.criarGrupo(nome, userLogado);
        CaixinhaBuilder builder = new CaixinhaBuilder();

        grupo.setCaixinha(builder.build());

        grupo = fachada.cadastrarGrupo(grupo);
        fachada.cadastrarParticipante(new Participante(grupo), grupoUser);

    }

    // Criar a caixinha é só pegar esses valores
    public static void caixinhaConfig(Grupo grupo) {

        CaixinhaBuilder builder = new CaixinhaBuilder();

        grupo.setCaixinha(builder.build());

    }

    public static void listarLancamentosTela(GrupoComponent component) {
        List<Lancamento> lancamentos = fachada.lancamentos(component);

        System.out.println("\n\nLANCAMENTOS:\n\n");

        lancamentos.forEach(System.out::println);

    }

    public static void listaGruposTela() {

        System.out.println("\n GRUPOS: \n");

        // Puxar os participantes para mostrar na tela
        List<Participante> participantes = fachada.participantes(grupoUser);
        int x = 1;

        System.out.println("Para entrar digite o numero do grupo.");
        for (Participante p : participantes) {
            System.out.println(
                    "id = " + x++ + " Grupo = " + p.getParticipante().getNome());
        }

        System.out.print("Para sair digite -1\nOpcao: ");
        short valor = sc.nextShort();
        limparBuffer(sc);

        if (valor > 0 && valor <= participantes.size()) {
            telaGrupo(participantes.get(valor - 1).getParticipante().getId());
            return;
        }

    }

    public static void telaGrupo(Long id) {

        Grupo grupo = fachada.buscarGrupo(id);

        BigDecimal saldo = calcularSaldo(grupo);
        short opcao = 0;

        int x = 1;
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Digite:\n")
                .append(x++ + " - Fazer lancamento\n")
                .append(x++ + " - Listar lancamentos\n")
                .append(x++ + " - Listar Usuarios\n");

        do {
            System.out.println(grupo.getNome() + "----------" + "saldo: " + saldo);

            System.out.print(sBuilder.toString());
            System.out.print("Ou digite 0 para sair\nOpcao: ");
            opcao = sc.nextShort();
            limparBuffer(sc);

            switch (opcao) {
                case 1:
                    cadastroLancamentoTela(grupo);
                    saldo = calcularSaldo(grupo);
                    break;
                case 2:
                    listarLancamentosTela(grupo);
                    break;
                case 3:
                    listaParticipantes(grupo);
                    break;
            }

        } while (opcao != 0);

    }

    public static void listaParticipantes(Grupo grupo) {

        System.out.println("--------- PARTICIPANTES: --------- ");
        List<Participante> participantes = fachada.participantes(grupo);

        participantes.forEach(System.out::println);

        System.out.println();

    }

    public static void cadastroLancamentoTela(Grupo grupo) {

        grupo.setCaixinha(fachada.caixinha(grupo));
        System.out.println("FAZER LANCAMENTO: ");

        System.out.print("Descricao:");
        String desc = sc.nextLine();

        System.out.print("Valor:");
        BigDecimal valor = sc.nextBigDecimal();

        int x = 0;

        OperacoesEnum valores[] = OperacoesEnum.values();

        for (OperacoesEnum val : valores) {

            System.out.println(x + " " + val);
        }

        System.out.print("Selecione a operacao: ");
        int opcao = sc.nextInt();

        limparBuffer(sc);

        LancamentoCommand command;

        if (valores[opcao] == OperacoesEnum.DEBITO) {
            command = new DebitoCommand(grupo, valor, userLogado, desc, new LancamentoSemItemsFactory());
        } else {
            command = new CreditoCommand(grupo, valor, userLogado, desc, new LancamentoSemItemsFactory());
        }

        command.executar();

        fachada.salvarLancamento(command.getLancamento());

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
        fachada.cadastrarParticipante(new Participante(grupoA), (Grupo) user1.getParente());
        // FIM

        // Cadastrar Participante INICIO
        fachada.cadastrarParticipante(new Participante(user2), grupoA);
        // FIM
        fachada.cadastrarParticipante(new Participante(user3), grupoA);
        fachada.cadastrarParticipante(new Participante(user4), grupoA);
        fachada.cadastrarParticipante(new Participante(user5), grupoA);
        fachada.cadastrarParticipante(new Participante(user6), grupoA);

        LancamentoCommand command = new DebitoCommand(grupoA, BigDecimal.valueOf(10.00), user1, "Agua",
                new LancamentoSemItemsFactory());

        command.executar();

        LancamentoCommand command2 = new CreditoCommand(grupoA, BigDecimal.valueOf(5.00), user4, "minha parte da agua",
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
        Item item3 = new Item("Refrigerante", 0, 10, "Refrigerante de 2L marcas variaveis", BigDecimal.valueOf(51.00));

        builder.items(Arrays.asList(item1, item2, item3));
        builder.fechamento(LocalDate.of(2023, 6, 10));
        builder.eMensal(false);
        Grupo grupoB = grupoFachada.criarGrupo("Churrasco final de semana", user1);
        grupoB.setCaixinha(builder.build());

        grupoB = fachada.cadastrarGrupo(grupoB);
        fachada.cadastrarParticipante(new Participante(grupoB), (Grupo) user1.getParente());

        fachada.cadastrarParticipante(new Participante(user2), grupoB);
        fachada.cadastrarParticipante(new Participante(user3), grupoB);
        fachada.cadastrarParticipante(new Participante(user4), grupoB);

    }

}
