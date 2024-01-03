package views;

import entities.Denuncia;
import entities.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Home {
    ArrayList<Usuario> cadastroUsuario;
    ArrayList<Usuario> cadastroDenuncia;
    public Home(){
        this.cadastroUsuario = new ArrayList<>();
        this.cadastroDenuncia = new ArrayList<>();
    }

    public void iniciarSistema(){
        Scanner scanner = new Scanner(System.in);
        CadastroUsuario cadastroU = new CadastroUsuario();
        CadastroDenuncia cadastroD = new CadastroDenuncia();
        Usuario novoUsuario = null;
        Denuncia novaDenuncia = null;
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
                    do {
                        int opUsuario = 0;
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
                    } while (opcao != 5);
                    break;
                case 2:
                    do {
                        int opDenuncia= 0;
                        System.out.println(" ------------- NOTIFICA -------------");
                        System.out.println("1. Cadastrar Denuncia");
                        System.out.println("2. Excluir Denuncia");
                        System.out.println("3. Editar Denuncia");
                        System.out.println("4. Visualizar Denuncia");
                        System.out.println("5. Sair");
                        System.out.println("Escolha uma opção:");

                        opDenuncia = scanner.nextInt();

                        switch (opDenuncia) {
                            case 1:
                                System.out.println("------------- Cadastrar Denuncia -------------");
                                novaDenuncia = cadastroD.cadastrarDenuncia();
                                break;
                            case 2:
                                System.out.println("------------- Excluir Denuncia -------------");
                                break;
                            case 3:
                                System.out.println("------------- Editar Denuncia -------------");
                                break;
                            case 4:
                                System.out.println("------------- Visualizar Denuncia -------------");
                                if (novaDenuncia == null) {
                                    System.out.println("Nenhuma denúncia cadastrada ainda.");
                                } else {
                                    cadastroD.visualizarDenuncia(novaDenuncia);
                                }
                                break;
                            case 5:
                                System.out.println("Saindo...");
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                    } while (opcao != 5);
                    break;
                case 3:
                    System.out.println("------------- Feed -------------");
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

}
