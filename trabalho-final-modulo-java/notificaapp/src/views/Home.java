package views;

import entities.Denuncia;
import entities.Localizacao;
import entities.Usuario;
import entities.enums.Categoria;
import entities.enums.Situacao;
import exceptions.InvalidInputException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Home {
    private ArrayList<Usuario> cadastroUsuario;
    private ArrayList<Usuario> cadastroDenuncia;
    private Scanner scanner;
    private CadastroUsuario cadastroU;
    private HashMap<Integer, Denuncia> listagemDenuncia;

    public Home() {
        this.cadastroUsuario = new ArrayList<>();
        this.cadastroDenuncia = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.cadastroU = new CadastroUsuario();
        this.listagemDenuncia = new HashMap<>();
    }


    public void iniciarSistema() {
        Usuario novoUsuario = null;
        int opcao = 0;

        //IMPLEMENTANDO MENU NUMERICO
        do {
            System.out.println(" ------------- NOTIFICA -------------");
            System.out.println("1. Usuário");
            System.out.println("2. Denúncia");
            System.out.println("3. Feed");
            System.out.println("4. Sair");
            System.out.println("Escolha uma opção:");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    int opUsuario = 0;

                    do {
                        System.out.println(" ------------- NOTIFICA -------------");
                        System.out.println("1. Cadastrar Conta");
                        System.out.println("2. Excluir Conta");
                        System.out.println("3. Editar Conta");
                        System.out.println("4. Visualizar Conta");
                        System.out.println("5. Sair");
                        System.out.println("Escolha uma opção:");

                        opUsuario = scanner.nextInt();
                        switch (opUsuario) {
                            case 1:
                                System.out.println("------------- Cadastrar Conta -------------");
                                novoUsuario = cadastroU.cadastrarUsuario();
                                break;
                            case 2:
                                System.out.println("------------- Excluir Conta -------------");
                                break;
                            case 3:
                                System.out.println("------------- Editar Conta -------------");
                                break;
                            case 4:
                                System.out.println("------------- Visualizar Conta -------------");
                                if (novoUsuario == null) {
                                    System.out.println("Nenhum usuário cadastrado ainda.");
                                } else {
                                    cadastroU.visualizarUsuario(novoUsuario);
                                }
                                break;
                            case 5:
                                System.out.println("Saindo...");
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                    } while (opUsuario != 5);
                    break;
                case 2:
                    int opDenuncia = 0;
                    do {
                        CadastroDenuncia cadastroDenuncia = new CadastroDenuncia();

                        Denuncia denuncia = null;

                        int idDenuncia = 0;

                        System.out.println(" ------------- NOTIFICA -------------");
                        System.out.println("1. Cadastrar Denuncia");
                        System.out.println("2. Excluir Denuncia");
                        System.out.println("3. Editar Denuncia");
                        System.out.println("4. Visualizar Denuncias");
                        System.out.println("5. Sair");
                        System.out.println("Escolha uma opção:");

                        opDenuncia = scanner.nextInt();

                        switch (opDenuncia) {
                            case 1:
                                System.out.println("------------- Cadastrar Denúncia -------------");

                                denuncia = cadastroDenuncia.cadastrarDenuncia();

                                if (denuncia != null) {
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
                                    listagemDenuncia.forEach((_index, itemDenuncia) -> {
                                        cadastroDenuncia.visualizarDenuncia(itemDenuncia);
                                    });
                                }
                                break;
                            case 5:
                                System.out.println("Saindo...");
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                    } while (opDenuncia != 5);
                    break;
                case 3:
                    System.out.println("------------- Feed -------------");

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

        scanner.close();
    }

    private void opcaoFeed() {

        int opFeed;

        do {
            System.out.println(" ------------- FEED -------------");
            listagemDenuncia.forEach((_index, itemDenuncia) -> {
                System.out.print(_index + ": ");
                itemDenuncia.imprimirDenunciaFeed();
            });

            System.out.println("Digite o número da denúncia\nque você deseja reagir");
            System.out.println("0. Sair");

            opFeed = scanner.nextInt();

            if (opFeed == 0)
                System.out.println("Saindo...");

            if (listagemDenuncia.containsKey(opFeed)) {

                int opReagir;
                do {
                    System.out.println(" ------------- Detalhes da denuncia -------------");

                    listagemDenuncia.get(opFeed).imprimirDetalhesDenunciaFeed();

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
                            }else{
                                listagemDenuncia.get(opFeed).comentar(comentario);
                            }
                            break;
                        case 3:
                            System.out.println("Saindo...");
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                } while (opReagir != 3);
            }else{
                System.out.println("Opção inválida");
            }
        } while (opFeed != 0);
    }
}
