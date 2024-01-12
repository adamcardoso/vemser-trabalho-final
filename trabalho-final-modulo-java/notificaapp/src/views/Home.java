package views;

import exceptions.DataBaseException;
import models.Denuncia;
import models.Localizacao;
import models.Usuario;
import models.enums.*;
import repositories.impl.UsuarioRepositoryImpl;
import services.impl.UsuarioServicesImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Home {
    Scanner input = new Scanner(System.in);
    UsuarioServicesImpl usuarioServices = new UsuarioServicesImpl();
    UsuarioRepositoryImpl usuarioRepository = new UsuarioRepositoryImpl();
    private static final String OPCAO_INVALIDA_MSG = "Opção inválida!";
    private static final String SAINDO_DO_SISTEMA_MSG = "Saindo do sistema...";
    private static final String ESCOLHA_OPCAO_MSG = "Escolha uma opção: ";
    private static final String VOLTANDO = "Voltando... ";
    private static final String CABECALHO_NOTIFICA_MSG = "------------- NOTIFICA -------------";
    private boolean usuarioLogado = false;

    public void displayLoginMenu() {
        int opMenuLogin;
        do {
            System.out.println("\n\n" + CABECALHO_NOTIFICA_MSG);
            System.out.println("1. Fazer login no sistema");
            System.out.println("2. Entrar sem login");
            System.out.println("3. Cadastrar");
            System.out.println("4. Sair");
            System.out.println(ESCOLHA_OPCAO_MSG);
            opMenuLogin = input.nextInt();

            switch (opMenuLogin){
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    usuarioLogado = false;
                    exibirMenuPrincipal();
                    break;
                case 3:
                    System.out.println("3. Cadastrar");
                    break;
                case 4:
                    System.out.println(SAINDO_DO_SISTEMA_MSG);
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
            }
        }while (opMenuLogin != 4);


    }
    private void exibirMenuPrincipal() {
        System.out.println(CABECALHO_NOTIFICA_MSG);
        if (usuarioLogado) {
            int opMenuPrincLogado;
            do {
                System.out.println("1. Usuário");
                System.out.println("2. Denúncia");
                System.out.println("3. Feed");
                System.out.println("4. Estatística");
                System.out.println("5. Voltar");
                System.out.println("6. Sair");
                System.out.println(ESCOLHA_OPCAO_MSG);
                opMenuPrincLogado = input.nextInt();
                switch (opMenuPrincLogado){
                    case 1:
                        exibirMenuUsuario();
                        break;
                    case 2:
                        exibirMenuDenuncia();
                        break;
                    case 3:
                        System.out.println("3. Feed");
                        break;
                    case 4:
                        System.out.println("4. Estatística");
                        break;
                    case 5:
                        displayLoginMenu();
                        System.out.println(VOLTANDO);
                        break;
                    case 6:
                        System.out.println(SAINDO_DO_SISTEMA_MSG);
                        break;
                    default:
                        System.out.println(OPCAO_INVALIDA_MSG);
                }
            }while (opMenuPrincLogado != 5);
        }else {
            int opMenuPrincNLogado;
            do {
                System.out.println("1. Feed");
                System.out.println("2. Estatística");
                System.out.println("3. Voltar");
                System.out.println("4. Sair");
                System.out.println(ESCOLHA_OPCAO_MSG);
                opMenuPrincNLogado = input.nextInt();
                switch (opMenuPrincNLogado){
                    case 1:
                        System.out.println("1. Feed");
                        break;
                    case 2:
                        System.out.println("2. Estatística");
                        break;
                    case 3:
                        displayLoginMenu();
                        System.out.println(VOLTANDO);
                        break;
                    case 4:
                        System.out.println(SAINDO_DO_SISTEMA_MSG);
                        break;
                    default:
                        System.out.println(OPCAO_INVALIDA_MSG);
                }
            }while (opMenuPrincNLogado != 4);

        }


    }

    private void exibirMenuUsuario(){
        int opMenuUsuario;
        do {
            System.out.println(CABECALHO_NOTIFICA_MSG);
            System.out.println("1. Excluir Conta");
            System.out.println("2. Editar Conta");
            System.out.println("3. Visualizar Conta");
            System.out.println("4. Voltar");
            System.out.println("5. Sair");
            System.out.println(ESCOLHA_OPCAO_MSG);
            opMenuUsuario = input.nextInt();
            switch (opMenuUsuario){
                case 1:
                    System.out.println("1. Excluir Conta");
                    break;
                case 2:
                    System.out.println("2. Editar Conta");
                    break;
                case 3:
                    System.out.println("3. Visualizar Conta");
                    break;
                case 4:
                    exibirMenuPrincipal();
                    System.out.println(VOLTANDO);
                    break;
                case 5:
                    System.out.println(SAINDO_DO_SISTEMA_MSG);
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
            }
        }while (opMenuUsuario != 5);

    }
    private void exibirMenuDenuncia(){
        int opMenuDenuncia;
        do {
            System.out.println(CABECALHO_NOTIFICA_MSG);
            System.out.println("1. Cadastrar Denuncia");
            System.out.println("2. Excluir Denuncia");
            System.out.println("3. Editar Denuncia");
            System.out.println("4. Visualizar Denuncias");
            System.out.println("5. Voltar");
            System.out.println("6. Sair");
            System.out.println(ESCOLHA_OPCAO_MSG);
            opMenuDenuncia = input.nextInt();
            switch (opMenuDenuncia){
                case 1:
                    System.out.println("1. Cadastrar Denuncia");
                    break;
                case 2:
                    System.out.println("2. Excluir Denuncia");
                    break;
                case 3:
                    System.out.println("3. Editar Denuncia");
                    break;
                case 4:
                    exibirMenuPrincipal();
                    System.out.println(VOLTANDO);
                    break;
                case 5:
                    System.out.println(SAINDO_DO_SISTEMA_MSG);
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
            }
        }while (opMenuDenuncia != 5);
    }

    private boolean fazerLogin(){
        System.out.print("Digite seu nome de usuário: ");
        String nomeUsuario = input.next();
        System.out.print("Digite sua senha: ");
        String senha = input.next();

        try {
            Usuario usuario = usuarioRepository.fazerLogin(nomeUsuario, senha);
            if (usuario != null) {
                System.out.println("Login bem-sucedido!");
                usuarioLogado = true;
                exibirMenuPrincipal();
                return true;
            } else {
                System.out.println("Nome de usuário ou senha incorretos. Tente novamente.");
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }

        return false;
    }
}

    /*private final HashMap<Integer, Usuario> listagemUsuario;
    private final Scanner scanner;
    private final CadastroUsuario cadastroU;
    private final HashMap<Integer, Denuncia> listagemDenuncia;
    private boolean usuarioLogado = false;
    private Usuario loginUsuario;
    private final EstatisticaUsuario estatisticaU;






    public void login() {
        int opLogin;

        do {
            displayLoginMenu();

            opLogin = scanner.nextInt();

            switch (opLogin) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    entrarSemLogin();
                    break;
                case 3:
                    cadastrarUsuario();
                    break;
                case 4:
                    System.out.println(SAINDO_DO_SISTEMA_MSG);
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
            }
        } while (opLogin != 4);

        scanner.close();
    }

    private void displayLoginMenu() {
        System.out.println("\n\n" + CABECALHO_NOTIFICA_MSG);
        System.out.println("1. Fazer login no sistema");
        System.out.println("2. Entrar sem login");
        System.out.println("3. Cadastrar");
        System.out.println("4. Sair");
        System.out.println(ESCOLHA_OPCAO_MSG);
    }

    private void fazerLogin() {
        System.out.println("Digite seu nome: ");
        String nomeUsuario = scanner.next();
        System.out.println("Digite sua senha: ");
        String senhaUsuario = scanner.next();

        for (Map.Entry<Integer, Usuario> usuarioEntry : listagemUsuario.entrySet()) {
            Usuario u = usuarioEntry.getValue();
            if (u.getNomeUsuario().equals(nomeUsuario) && u.getSenhaUsuario().equals(senhaUsuario)) {
                System.out.println("Login efetuado com sucesso");
                loginUsuario = listagemUsuario.get(u.getIdUsuario());
                usuarioLogado = true;
                this.iniciarSistema();
                return;
            }
        }

        System.out.println("Nome do usuário não encontrado ou senha incorreta");
    }

    private void entrarSemLogin() {
        System.out.println("Fazer denúncia sem estar logado no sistema");
        System.out.println("Digite o nome do usuário");
        String nomeUsuarioSemLogin = scanner.next();
        usuarioLogado = false;
        this.loginUsuario = new Usuario(1, nomeUsuarioSemLogin);
        this.iniciarSistema();
    }

    private void cadastrarUsuario() {
        loginUsuario = cadastroU.cadastrarUsuario();
        listagemUsuario.put(loginUsuario.getIdUsuario(), loginUsuario);
    }

    private void exibirEstatisticas() {
        estatisticaU.exibirEstatisticas(new ArrayList<>(listagemUsuario.values()));
        iniciarSistema();
    }

    public void iniciarSistema() {
        int opcao;

        do {
            exibirMenuPrincipal();

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    if (usuarioLogado) {
                        menuUsuario();
                    }else{
                        menuDenuncia();
                    }
                    break;
                case 2:
                    if (usuarioLogado) {
                        menuDenuncia();
                    }else{
                        opcaoFeed();
                    }
                    break;
                case 3:
                    if (usuarioLogado) {
                        opcaoFeed();
                    }else {
                        exibirEstatisticas();
                    }
                    break;
                case 4:
                    if (usuarioLogado) {
                        exibirEstatisticas();
                    }else {
                        System.out.println(SAINDO_DO_SISTEMA_MSG);
                    }
                    break;
                case 5:
                    if (usuarioLogado) {
                        System.out.println(SAINDO_DO_SISTEMA_MSG);
                    }else {
                        System.out.println(OPCAO_INVALIDA_MSG);
                    }
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
                    break;
            }
        } while (usuarioLogado && opcao != 5 || !usuarioLogado && opcao!=4 && opcao<5);
    }

    private void exibirMenuPrincipal() {
        System.out.println(CABECALHO_NOTIFICA_MSG);
        if (usuarioLogado) {
            System.out.println("1. Usuário");
            System.out.println("2. Denúncia");
            System.out.println("3. Feed");
            System.out.println("4. Estatística");
            System.out.println("5. Sair");
            System.out.println(ESCOLHA_OPCAO_MSG);
        }else {
            System.out.println("1. Denúncia");
            System.out.println("2. Feed");
            System.out.println("3. Estatística");
            System.out.println("4. Sair");
            System.out.println(ESCOLHA_OPCAO_MSG);
        }

    }

    private void menuUsuario() {
        int opUsuario;

        do {
            exibirMenuUsuario();

            opUsuario = scanner.nextInt();

            switch (opUsuario) {
                case 1:
                    excluirConta();
                    break;
                case 2:
                    editarConta();
                    break;
                case 3:
                    visualizarConta();
                    break;
                case 4:
                    iniciarSistema();
                    break;
                case 5:
                    System.out.println(SAINDO_DO_SISTEMA_MSG);
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
                    break;
            }
        } while (opUsuario != 5);
    }

    private void exibirMenuUsuario() {
        System.out.println(CABECALHO_NOTIFICA_MSG);
        System.out.println("1. Excluir Conta");
        System.out.println("2. Editar Conta");
        System.out.println("3. Visualizar Conta");
        System.out.println("4. Voltar");
        System.out.println("5. Sair");
        System.out.println(ESCOLHA_OPCAO_MSG);
    }

    private void excluirConta() {
        System.out.println("------------- Excluir Conta -------------");
        if (loginUsuario != null) {
            cadastroU.excluirUsuario(loginUsuario.getIdUsuario(), listagemUsuario);
            usuarioLogado = false;
            loginUsuario = null; // Como o usuário foi deletado, seta para null
            login();
        } else {
            System.out.println("Usuário não está logado.");
        }
    }

    private void editarConta() {
        System.out.println("------------- Editar Conta -------------");
        if (loginUsuario != null) {
            cadastroU.editarUsuario(loginUsuario.getIdUsuario(), loginUsuario);
        } else {
            System.out.println("Usuário não está logado.");
        }
    }

    private void visualizarConta() {
        System.out.println("------------- Visualizar Conta -------------");
        if (loginUsuario == null) {
            System.out.println("Nenhum usuário cadastrado ainda.");
        } else {
            cadastroU.visualizarUsuario(loginUsuario);
        }
    }

    private void menuDenuncia() {
        CadastroDenuncia cadastroDenuncia = new CadastroDenuncia(); // Cria a instancia fora do loop

        int opDenuncia;
        do {
            exibirMenuDenuncia();

            opDenuncia = scanner.nextInt();

            switch (opDenuncia) {
                case 1:
                    cadastrarDenuncia(cadastroDenuncia);
                    break;
                case 2:
                    excluirDenuncia(cadastroDenuncia);
                    break;
                case 3:
                    editarDenuncia(cadastroDenuncia);
                    break;
                case 4:
                    visualizarDenuncias(cadastroDenuncia, loginUsuario.getIdUsuario());
                    break;
                case 5:
                    iniciarSistema();
                    break;
                case 6:
                    System.out.println(SAINDO_DO_SISTEMA_MSG);
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
                    break;
            }
        } while (opDenuncia != 6);
    }

    private void exibirMenuDenuncia() {
        System.out.println(CABECALHO_NOTIFICA_MSG);
        System.out.println("1. Cadastrar Denuncia");
        System.out.println("2. Excluir Denuncia");
        System.out.println("3. Editar Denuncia");
        System.out.println("4. Visualizar Denuncias");
        System.out.println("5. Voltar");
        System.out.println("6. Sair");
        System.out.println(ESCOLHA_OPCAO_MSG);
    }

    private void cadastrarDenuncia(CadastroDenuncia cadastroDenuncia) {
        System.out.println("------------- Cadastrar Denúncia -------------");
        Denuncia denuncia = cadastroDenuncia.cadastrarDenuncia();

        if (denuncia != null) {
            denuncia.setUsuario(loginUsuario);
            listagemDenuncia.put(denuncia.getIdDenuncia(), denuncia);
        }
    }

    private void excluirDenuncia(CadastroDenuncia cadastroDenuncia) {
        System.out.println("------------- Excluir Denúncia -------------");
        System.out.println("Digite o ID da Denúncia: ");
        int idDenuncia = scanner.nextInt();
        Denuncia denuncia = listagemDenuncia.get(idDenuncia);

        if (denuncia != null) {
            System.out.println("Denúncia encontrada! Excluindo...");
            cadastroDenuncia.excluirDenuncia(idDenuncia, listagemDenuncia);
        } else {
            System.out.println("Denúncia não encontrada!");
        }
    }

    private void editarDenuncia(CadastroDenuncia cadastroDenuncia) {
        System.out.println("------------- Editar Denúncia -------------");
        System.out.println("Digite o ID da Denuncia: ");
        int idDenuncia = scanner.nextInt();
        Denuncia denuncia = listagemDenuncia.get(idDenuncia);

        if (denuncia != null) {
            System.out.println("Denúncia encontrada! Siga as instruções para editar.");

            denuncia = cadastroDenuncia.editarDenuncia(idDenuncia, denuncia);

            if (denuncia != null) {
                denuncia.setUsuario(loginUsuario);
                listagemDenuncia.put(idDenuncia, denuncia);
            }
        } else {
            System.out.println("Denúncia não encontrada!");
        }
    }

    private void visualizarDenuncias(CadastroDenuncia cadastroDenuncia, int idUsuario) {
        System.out.println("------------- Visualizar Denúncias -------------");

        boolean denunciaEncontrada = false;
        for (Map.Entry<Integer, Denuncia> denunciaEntry : listagemDenuncia.entrySet()) {
            Denuncia denuncia = denunciaEntry.getValue();
            if (denuncia.getUsuario().getIdUsuario() == idUsuario) {
                cadastroDenuncia.visualizarDenuncia(denuncia);
                denunciaEncontrada = true;
            }
        }
        if (!denunciaEncontrada) {
            System.out.println("Nenhuma denúncia encontrada para o usuário " + loginUsuario.getNomeUsuario());
        }
    }


    private void opcaoFeed() {
        int opFeed;
        do {
            System.out.println(" ------------- FEED -------------");
            imprimirListagemDenuncia();

            System.out.println("Digite o número da denúncia\nque você deseja reagir");
            System.out.println("0. Voltar");

            opFeed = scanner.nextInt();

            if (opFeed == 0) {
                System.out.println(VOLTANDO);
                this.iniciarSistema();
            } else if (listagemDenuncia.containsKey(opFeed)) {
                processarDenuncia(opFeed);
            } else {
                System.out.println(OPCAO_INVALIDA_MSG);
            }

        } while (opFeed != 0);
    }

    private void imprimirListagemDenuncia() {
        listagemDenuncia.forEach((index, itemDenuncia) -> {
            System.out.print(index + ": ");
            itemDenuncia.imprimirDenunciaFeed();
            System.out.println("................................");
        });
    }

    private void processarDenuncia(int opFeed) {
        int opReagir;
        boolean voltar = false;

        do {
            System.out.println(" ------------- Detalhes da denuncia -------------");
            listagemDenuncia.get(opFeed).imprimirDetalhesDenunciaFeed();

            if (!usuarioLogado) {
                opReagir = processarDenunciaSemLogin();
            } else {
                opReagir = processarDenunciaComLogin();
            }

            switch (opReagir) {
                case 1:
                    listagemDenuncia.get(opFeed).curtirDenuncia();
                    break;
                case 2:
                    processarComentario(opFeed);
                    break;
                case 3:
                    if (usuarioLogado) {
                        listagemDenuncia.get(opFeed).validarDenuncia();
                    }else {
                        System.out.println("Voltando...");
                        voltar = true;
                    }
                    break;
                case 4:
                    System.out.println("Voltando...");
                    voltar = true;
                    break;
                case 5:
                    if (!usuarioLogado) {
                        System.out.println(SAINDO_DO_SISTEMA_MSG);
                        return; // Sai do método
                    }
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
                    break;
            }
        } while (!voltar);
    }

    private int processarDenunciaSemLogin() {
        System.out.println("1. Curtir denuncia");
        System.out.println("2. Escrever um comentário");
        System.out.println("3. Sair");
        return scanner.nextInt();
    }

    private int processarDenunciaComLogin() {
        System.out.println("1. Curtir denuncia");
        System.out.println("2. Escrever um comentário");
        System.out.println("3. Validar denúncia");
        System.out.println("4. Voltar");
        System.out.println("5. Sair");
        return scanner.nextInt();
    }

    private void processarComentario(int opFeed) {
        System.out.println("Digite o comentário que deseja realizar: ");
        String comentario = scanner.next();

        if (comentario.isEmpty()) {
            System.out.println("O campo comentário não pode estar vazio");
        } else {
            listagemDenuncia.get(opFeed).comentar(comentario);
        }
    }*/
