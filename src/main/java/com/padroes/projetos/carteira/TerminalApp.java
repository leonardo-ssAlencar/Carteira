package com.padroes.projetos.carteira;

import java.util.Scanner;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.service.AplicacaoFachada;

public class TerminalApp {
    private static AplicacaoFachada fachada;

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(CarteiraApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        fachada = context.getBean(AplicacaoFachada.class);

        // Scanner sc = new Scanner(System.in);
        // int opcao = 0;

        // do {

        // System.out.println("1 - login");
        // System.out.println("2 - cadastro");
        // System.out.println("3 - sair");
        // System.out.println("----------\nEscolha: ");

        // switch (opcao) {
        // case 1:
        // telaLogin();
        // break;
        // case 2:
        // telaCadastro();
        // break;
        // }

        // } while (opcao != 3);

        // sc.close();

        Usuario user = new Usuario("Leonardo", "999999999", "leonardo@carteira.com", "123");

        fachada.cadastrarUsuario(user);

        System.out.println(fachada.validarUsuario("leonardo@carteira.com", "123"));

    }

    public static void telaCadastro() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Cadastrando um novo Usuario:");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("email: ");
        String email = sc.nextLine();

        System.out.println("telefone: ");
        String telefone = sc.nextLine();

        System.out.println("senha: ");
        String senha = sc.nextLine();

        sc.close();

        Usuario user = new Usuario(nome, telefone, email, senha);
        fachada.cadastrarUsuario(user);

    }

    public static void telaLogin() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Cadastrando um novo Usuario:");

        System.out.print("email: ");
        String email = sc.nextLine();

        System.out.print("senha: ");
        String senha = sc.nextLine();

        sc.close();

        fachada.validarUsuario(email, senha);

    }

}
