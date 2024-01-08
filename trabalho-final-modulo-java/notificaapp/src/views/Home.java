package views;

import entities.Denuncia;
import entities.Localizacao;
import entities.Usuario;
import entities.enums.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Home {
    private final HashMap<Integer, Usuario> listagemUsuario;
    private final Scanner scanner;
    private final CadastroUsuario cadastroU;
    private final HashMap<Integer, Denuncia> listagemDenuncia;
    private boolean usuarioLogado = false;
    private Usuario loginUsuario;
    private final EstatisticaUsuario estatisticaU;

    private static final String OPCAO_INVALIDA_MSG = "Opção inválida!";
    private static final String SAINDO_DO_SISTEMA_MSG = "Saindo do sistema...";
    private static final String ESCOLHA_OPCAO_MSG = "Escolha uma opção: ";
    private static final String VOLTANDO = "Voltando... ";
    private static final String CABECALHO_NOTIFICA_MSG = "------------- NOTIFICA -------------";

    public Home() {
        this.listagemUsuario = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.cadastroU = new CadastroUsuario();
        this.listagemDenuncia = new HashMap<>();
        this.estatisticaU = new EstatisticaUsuario();

        // Usuários
        Usuario testeUsuario = new Usuario(123, "jean", "122339", "A1@abcd", Etnia.INDIGENA, null, ClasseSocial.E, Genero.MASCULINO, TipoUsuario.INDIVIDUAL);
        listagemUsuario.put(testeUsuario.getIdUsuario(), testeUsuario);

        Usuario testeUsuario1 = new Usuario(124, "maria", "122334469", "B2@efgh", Etnia.PARDO, null, ClasseSocial.E, Genero.FEMININO, TipoUsuario.INDIVIDUAL);
        listagemUsuario.put(testeUsuario1.getIdUsuario(), testeUsuario1);

        Usuario testeUsuario2 = new Usuario(125, "joao", "122334479", "C3@ijkl", Etnia.PRETO, null, ClasseSocial.C, Genero.MASCULINO, TipoUsuario.INDIVIDUAL);
        listagemUsuario.put(testeUsuario2.getIdUsuario(), testeUsuario2);

        Usuario testeUsuario3 = new Usuario(126, "ana", "122334489", "D4@mnop", Etnia.PRETO, null, ClasseSocial.D, Genero.FEMININO, TipoUsuario.INDIVIDUAL);
        listagemUsuario.put(testeUsuario3.getIdUsuario(), testeUsuario3);

        Usuario testeUsuario4 = new Usuario(127, "marta", "122334499", "E4@mnop", Etnia.PRETO, null, ClasseSocial.E, Genero.OUTRO, TipoUsuario.INDIVIDUAL);
        listagemUsuario.put(testeUsuario4.getIdUsuario(), testeUsuario4);

        Usuario testeUsuario5 = new Usuario(128, "pedro", "122334199", "F4@mnop", Etnia.BRANCO, null, ClasseSocial.D, Genero.MASCULINO, TipoUsuario.INDIVIDUAL);
        listagemUsuario.put(testeUsuario5.getIdUsuario(), testeUsuario5);

        Usuario testeUsuario6 = new Usuario(129, "hinata", "122334299", "G4@mnop", Etnia.AMARELO, null, ClasseSocial.C, Genero.FEMININO, TipoUsuario.INDIVIDUAL);
        listagemUsuario.put(testeUsuario6.getIdUsuario(), testeUsuario6);

        // Denúncias associadas aos usuários
        listagemDenuncia.put(1, new Denuncia(1, "Esgoto ao Ar Livre", new Localizacao(123, 123),
                testeUsuario5, LocalDateTime.of(2023, 7, 18, 8, 45), Situacao.ABERTO, Categoria.SANEAMENTO_BASICO
        ));
        listagemDenuncia.put(2, new Denuncia(2, "Vazamento de óleo", new Localizacao(456, 789),
                testeUsuario3, LocalDateTime.of(2023, 6, 5, 11, 20), Situacao.ABERTO, Categoria.GESTAO_RESIDUOS
        ));
        listagemDenuncia.put(3, new Denuncia(3, "Falta de Água", new Localizacao(111, 333),
                testeUsuario2, LocalDateTime.of(2023, 8, 20, 9, 0), Situacao.ABERTO, Categoria.AGUA_POTAVEL
        ));
        listagemDenuncia.put(4, new Denuncia(4, "Água Suja", new Localizacao(222, 444),
                testeUsuario6, LocalDateTime.of(2023, 9, 12, 14, 30), Situacao.ABERTO, Categoria.AGUA_POTAVEL
        ));

    }

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
    }
}