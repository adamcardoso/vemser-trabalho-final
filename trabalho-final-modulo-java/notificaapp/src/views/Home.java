package views;

import entities.Denuncia;
import entities.Usuario;
import entities.enums.ClasseSocial;
import entities.enums.Etnia;
import entities.enums.Genero;
import entities.enums.TipoUsuario;

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
    private static final String CABECALHO_NOTIFICA_MSG = "------------- NOTIFICA -------------";

    public Home() {
        this.listagemUsuario = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.cadastroU = new CadastroUsuario();
        this.listagemDenuncia = new HashMap<>();
        this.estatisticaU = new EstatisticaUsuario();

        //TESTES
        Usuario testeUsuario = new Usuario(123, "jean", "12233445", "A1@abcd", Etnia.INDIGENA, null, ClasseSocial.A, Genero.MASCULINO, TipoUsuario.INDIVIDUAL);
        loginUsuario = testeUsuario;
        listagemUsuario.put(testeUsuario.getIdUsuario(), testeUsuario);

        // Teste Usuario 1
        Usuario testeUsuario1 = new Usuario(124, "maria", "12233446", "B2@efgh", Etnia.BRANCO, null, ClasseSocial.B, Genero.FEMININO, TipoUsuario.INDIVIDUAL);
        listagemUsuario.put(testeUsuario1.getIdUsuario(), testeUsuario1);

        // Teste Usuario 2
        Usuario testeUsuario2 = new Usuario(125, "joao", "12233447", "C3@ijkl", Etnia.PARDO, null, ClasseSocial.C, Genero.MASCULINO, TipoUsuario.INDIVIDUAL);
        listagemUsuario.put(testeUsuario2.getIdUsuario(), testeUsuario2);

        // Teste Usuario 3
        Usuario testeUsuario3 = new Usuario(126, "ana", "12233448", "D4@mnop", Etnia.PRETO, null, ClasseSocial.D, Genero.FEMININO, TipoUsuario.INDIVIDUAL);
        listagemUsuario.put(testeUsuario3.getIdUsuario(), testeUsuario3);
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
        this.loginUsuario = new Usuario(nomeUsuarioSemLogin);
        this.iniciarSistema();
    }

    private void cadastrarUsuario() {
        loginUsuario = cadastroU.cadastrarUsuario();
        listagemUsuario.put(loginUsuario.getIdUsuario(), loginUsuario);
    }

    private void exibirEstatisticas() {
        estatisticaU.exibirEstatisticas(new ArrayList<>(listagemUsuario.values()));
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
                    }
                    break;
                case 2:
                    menuDenuncia();
                    break;
                case 3:
                    System.out.println("------------- Feed -------------");
                    opcaoFeed();
                    break;
                case 4:
                    exibirEstatisticas();
                    break;
                case 5:
                    System.out.println(SAINDO_DO_SISTEMA_MSG);
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
                    break;
            }
        } while (opcao != 5);
    }

    private void exibirMenuPrincipal() {
        System.out.println(CABECALHO_NOTIFICA_MSG);
        if (usuarioLogado) {
            System.out.println("1. Usuário");
        }
        System.out.println("2. Denúncia");
        System.out.println("3. Feed");
        System.out.println("4. Estatística");
        System.out.println("5. Sair");
        System.out.println(ESCOLHA_OPCAO_MSG);
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
                    visualizarDenuncias(cadastroDenuncia);
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

    private void visualizarDenuncias(CadastroDenuncia cadastroDenuncia) {
        System.out.println("------------- Visualizar Denúncias -------------");

        if (listagemDenuncia.isEmpty()) {
            System.out.println("Nenhuma denúncia cadastrada!");
        } else {
            listagemDenuncia.forEach((index, itemDenuncia) -> cadastroDenuncia.visualizarDenuncia(itemDenuncia));
        }
    }

    private void opcaoFeed() {
        int opFeed;
        do {
            System.out.println(" ------------- FEED -------------");
            imprimirListagemDenuncia();

            System.out.println("Digite o número da denúncia\nque você deseja reagir");
            System.out.println("0. Sair");

            opFeed = scanner.nextInt();

            if (opFeed == 0) {
                System.out.println(SAINDO_DO_SISTEMA_MSG);
                break; // Esse break é para sair do loop
            } else if (listagemDenuncia.containsKey(opFeed)) {
                processarDenuncia(opFeed);
            } else {
                System.out.println(OPCAO_INVALIDA_MSG);
            }

        } while (opFeed != -1); // Muda a condição de saída
    }

    private void imprimirListagemDenuncia() {
        listagemDenuncia.forEach((index, itemDenuncia) -> {
            System.out.print(index + ": ");
            itemDenuncia.imprimirDenunciaFeed();
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