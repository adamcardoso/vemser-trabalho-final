package views;

import entities.Denuncia;
import entities.Localizacao;
import entities.Usuario;
import entities.enums.*;

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

    public Home() {
        this.listagemUsuario = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.cadastroU = new CadastroUsuario();
        this.listagemDenuncia = new HashMap<>();

        //TESTES
        Usuario testeUsuario = new Usuario(123, "jean", "12233445", "A1@abcd", Etnia.INDIGENA, null, ClasseSocial.A, Genero.MASCULINO, TipoUsuario.INDIVIDUAL);
        loginUsuario = testeUsuario;
        listagemUsuario.put(testeUsuario.getIdUsuario(), testeUsuario);
    }

    public void login() {


        int opLogin;

        do {
            System.out.println(" \n\n------------- NOTIFICA -------------");
            System.out.println("1. fazer login no sistema");
            System.out.println("2. Entrar sem login");
            System.out.println("3. Cadastrar");
            System.out.println("4. Sair");

            opLogin = scanner.nextInt();

            switch (opLogin) {
                case 1:
                    System.out.println("Digite seu nome: ");
                    String nomeUsuario = scanner.next();
                    System.out.println("Digite sua senha: ");
                    String senhaUsuario = scanner.next();
                    for (Map.Entry<Integer, Usuario> usuario : listagemUsuario.entrySet()) {
                        Usuario u = usuario.getValue();
                        if (u.getNomeUsuario().equals(nomeUsuario)) {
                            if (u.getSenhaUsuario().equals(senhaUsuario)) {
                                System.out.println("Login efetuado com sucesso");
                                loginUsuario = listagemUsuario.get(u.getIdUsuario());
                                usuarioLogado = true;
                                this.iniciarSistema();
                            } else {
                                System.out.println("Senha incorreta");
                            }
                        }
                    }
                    System.out.println("Nome do usuario não encontrado");
                    break;
                case 2:
                    System.out.println("Fazer denuncia sem está logado no sistema");
                    System.out.println("Digite o nome do usuario");
                    String nomeUsuarioSemLogin = scanner.next();
                    usuarioLogado = false;
                    this.loginUsuario = new Usuario(nomeUsuarioSemLogin);
                    this.iniciarSistema();
                    break;
                case 3:
                    loginUsuario = cadastroU.cadastrarUsuario();
                    listagemUsuario.put(loginUsuario.getIdUsuario(), loginUsuario);
                    break;
                case 4:
                    System.out.println("saindo.............");
                default:
                    System.out.println("Comando inválido");

            }


        } while (opLogin != 4);

        scanner.close();
    }

    public void iniciarSistema() {
        int opcao;

        do {
            System.out.println(" ------------- NOTIFICA -------------");
            if (usuarioLogado) {
                System.out.println("1. Usuário");
            }
            System.out.println("2. Denúncia");
            System.out.println("3. Feed");
            System.out.println("4. Sair");
            System.out.println("Escolha uma opção:");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    if (usuarioLogado) {
                        int opUsuario;
                        do {

                            System.out.println(" ------------- NOTIFICA -------------");
                            System.out.println("1. Excluir Conta");
                            System.out.println("2. Editar Conta");
                            System.out.println("3. Visualizar Conta");
                            System.out.println("4. Voltar");
                            System.out.println("5. Sair");
                            System.out.println("Escolha uma opção:");

                            opUsuario = scanner.nextInt();
                            switch (opUsuario) {
                                case 1:
                                    System.out.println("------------- Excluir Conta -------------");
                                    cadastroU.excluirUsuario(loginUsuario.getIdUsuario(), listagemUsuario);
                                    usuarioLogado = false;
                                    login();
                                    break;
                                case 2:
                                    System.out.println("------------- Editar Conta -------------");
                                    cadastroU.editarUsuario(loginUsuario.getIdUsuario(), loginUsuario);
                                    break;
                                case 3:
                                    System.out.println("------------- Visualizar Conta -------------");
                                    if (loginUsuario == null) {
                                        System.out.println("Nenhum usuário cadastrado ainda.");
                                    } else {
                                        cadastroU.visualizarUsuario(loginUsuario);
                                    }
                                    break;
                                case 4:
                                    iniciarSistema();
                                    break;
                                case 5:
                                    System.out.println("Saindo...");
                                    break;
                                default:
                                    System.out.println("Opção inválida");
                                    break;
                            }
                        } while (opUsuario != 5);
                    }
                    break;
                case 2:
                    int opDenuncia;
                    do {
                        CadastroDenuncia cadastroDenuncia = new CadastroDenuncia();

                        Denuncia denuncia;

                        int idDenuncia;

                        System.out.println(" ------------- NOTIFICA -------------");
                        System.out.println("1. Cadastrar Denuncia");
                        System.out.println("2. Excluir Denuncia");
                        System.out.println("3. Editar Denuncia");
                        System.out.println("4. Visualizar Denuncias");
                        System.out.println("5. Voltar");
                        System.out.println("6. Sair");
                        System.out.println("Escolha uma opção:");

                        opDenuncia = scanner.nextInt();

                        switch (opDenuncia) {
                            case 1:
                                System.out.println("------------- Cadastrar Denúncia -------------");
                                denuncia = cadastroDenuncia.cadastrarDenuncia();

                                if (denuncia != null) {
                                    denuncia.setUsuario(loginUsuario);
                                    listagemDenuncia.put(denuncia.getIdDenuncia(), denuncia);
                                }
                                break;
                            case 2:
                                System.out.println("------------- Excluir Denúncia -------------");
                                System.out.println("Digite o ID da Denúncia: ");
                                idDenuncia = scanner.nextInt();
                                denuncia = listagemDenuncia.get(idDenuncia);

                                if (denuncia != null) {
                                    System.out.println("Denúncia encontrada! Excluindo...");
                                    cadastroDenuncia.excluirDenuncia(idDenuncia, listagemDenuncia);
                                } else {
                                    System.out.println("Denúncia não encontrada!");
                                }
                                break;
                            case 3:
                                System.out.println("------------- Editar Denúncia -------------");

                                System.out.println("Digite o ID da Denuncia: ");
                                idDenuncia = scanner.nextInt();
                                denuncia = listagemDenuncia.get(idDenuncia);

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
                                break;
                            case 4:
                                System.out.println("------------- Visualizar Denúncias -------------");

                                if (listagemDenuncia.isEmpty()) {
                                    System.out.println("Nenhuma denúncia cadastrada!");
                                } else {
                                    listagemDenuncia.forEach((_index, itemDenuncia) -> cadastroDenuncia.visualizarDenuncia(itemDenuncia));
                                }
                                break;
                            case 5:
                                iniciarSistema();
                                break;
                            case 6:
                                System.out.println("Saindo...");
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                    } while (opDenuncia != 5);
                    break;
                case 3:
                    System.out.println("------------- FEED -------------");
                    this.opcaoFeed();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 4);


    }

    private void opcaoFeed() {

        listagemDenuncia.put(
                1, new Denuncia(1, "denuncia1", new Localizacao(123, 123),
                        new Usuario(12, "usuario1", null, null, null, null, null, null, null),
                        null, null, null
                ));
        listagemDenuncia.put(
                2, new Denuncia(1, "denuncia2", new Localizacao(123, 123),
                        new Usuario(12, "usuario2", null, null, null, null, null, null, null),
                        null, null, null
                ));

        int opFeed;

        do {
            listagemDenuncia.forEach((_index, itemDenuncia) -> {
                System.out.print(_index + ": ");
                itemDenuncia.imprimirDenunciaFeed();
                System.out.println("................................");

            });

            System.out.println("Digite o número da denúncia\nque você deseja reagir");
            System.out.println("0. Sair");

            opFeed = scanner.nextInt();

            if (opFeed == 0)
                System.out.println("Saindo...");

            if (listagemDenuncia.containsKey(opFeed)) {

                int opReagir;
                boolean voltar = false;
                do {
                    System.out.println(" ------------- Detalhes da denuncia -------------");

                    listagemDenuncia.get(opFeed).imprimirDetalhesDenunciaFeed();
                    if (!usuarioLogado) {
                        System.out.println("1. Curtir denuncia");
                        System.out.println("2. Escrever um comentário");
                        System.out.println("3. Sair");
                        opReagir = scanner.nextInt();

                        switch (opReagir) {
                            case 1:
                                listagemDenuncia.get(opFeed).curtirDenuncia();
                                break;
                            case 2:
                                System.out.println("Digite o comentário que deseja realizar: ");
                                String comentario = scanner.next();

                                if (comentario.isEmpty()) {
                                    System.out.println("O campo comentário não pode estar vazio");
                                } else {
                                    listagemDenuncia.get(opFeed).comentar(comentario);
                                }
                                break;
                            case 3:
                                voltar = true;
                                System.out.println("Voltando...");
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                    } else {
                        System.out.println("1. Curtir denuncia");
                        System.out.println("2. Escrever um comentário");
                        System.out.println("3. Validar denúncia");
                        System.out.println("4. Voltar");
                        System.out.println("5. Sair");
                        opReagir = scanner.nextInt();

                        switch (opReagir) {
                            case 1:
                                listagemDenuncia.get(opFeed).curtirDenuncia();
                                break;
                            case 2:
                                System.out.println("Digite o comentário que deseja realizar: ");
                                String comentario = scanner.next();

                                if (comentario.isEmpty()) {
                                    System.out.println("O campo comentário não pode estar vazio");
                                } else {
                                    listagemDenuncia.get(opFeed).comentar(comentario);
                                }
                                break;
                            case 3:
                                listagemDenuncia.get(opFeed).validarDenuncia();
                                break;
                            case 4:
                                System.out.println("Voltando...");
                                voltar = true;
                                break;
                            case 5:
                                System.out.println("Saindo...");
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                    }

                } while (!voltar);
            } else {
                System.out.println("Opção inválida");
            }
        } while (opFeed != 0);
    }
}