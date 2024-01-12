package views;

import models.Usuario;
import services.impl.UsuarioServicesImpl;

import java.util.Scanner;

public class Home {
    Scanner input = new Scanner(System.in);
    UsuarioServicesImpl usuarioServices = new UsuarioServicesImpl();
    private static final String OPCAO_INVALIDA_MSG = "Opção inválida!";
    private static final String SAINDO_DO_SISTEMA_MSG = "Saindo do sistema...";
    private static final String ESCOLHA_OPCAO_MSG = "Escolha uma opção: ";
    private static final String VOLTANDO = "Voltando... ";
    private static final String CABECALHO_NOTIFICA_MSG = "------------- NOTIFICA -------------";

    private Usuario usuarioLogado = null;

    public void exibirLoginMenu() {
        int opMenuLogin;

        do {
            System.out.println("\n\n" + CABECALHO_NOTIFICA_MSG);
            System.out.println("1. Fazer login no sistema");
            System.out.println("2. Entrar sem login");
            System.out.println("3. Cadastrar");
            System.out.println("4. Sair");
            System.out.println(ESCOLHA_OPCAO_MSG);
            opMenuLogin = input.nextInt();

            switch (opMenuLogin) {
                case 1:
                    this.usuarioLogado = fazerLogin();

                    if (this.usuarioLogado != null) {
                        if (this.usuarioLogado.getIsAdmin()) {
                            this.exibirMenuPrincipalAdmin();
                        } else {
                            this.exibirMenuPrincipal();
                        }
                    }
                    break;
                case 2:
                    usuarioLogado = null;
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
        } while (opMenuLogin != 4);
    }

    private void exibirMenuPrincipal() {
        System.out.println(CABECALHO_NOTIFICA_MSG);

        if (usuarioLogado != null) {
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
                switch (opMenuPrincLogado) {
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
                        exibirLoginMenu();
                        System.out.println(VOLTANDO);
                        break;
                    case 6:
                        System.out.println(SAINDO_DO_SISTEMA_MSG);
                        break;
                    default:
                        System.out.println(OPCAO_INVALIDA_MSG);
                }
            } while (opMenuPrincLogado != 5);
        } else {
            int opMenuPrincNLogado;
            do {
                System.out.println("1. Feed");
                System.out.println("2. Estatística");
                System.out.println("3. Voltar");
                System.out.println("4. Sair");
                System.out.println(ESCOLHA_OPCAO_MSG);
                opMenuPrincNLogado = input.nextInt();
                switch (opMenuPrincNLogado) {
                    case 1:
                        System.out.println("1. Feed");
                        break;
                    case 2:
                        System.out.println("2. Estatística");
                        break;
                    case 3:
                        exibirLoginMenu();
                        System.out.println(VOLTANDO);
                        break;
                    case 4:
                        System.out.println(SAINDO_DO_SISTEMA_MSG);
                        break;
                    default:
                        System.out.println(OPCAO_INVALIDA_MSG);
                }
            } while (opMenuPrincNLogado != 4);
        }
    }

    private void exibirPainelAdministrativo() {
        System.out.println(CABECALHO_NOTIFICA_MSG);

        int opPainelAdministrativo;

        do {
            System.out.println("1. Ver usuários");
            System.out.println("2. Excluir usuário");
            System.out.println("3. Voltar");
            System.out.println("4. Sair");

            System.out.println(ESCOLHA_OPCAO_MSG);

            opPainelAdministrativo = input.nextInt();

            switch (opPainelAdministrativo) {
                case 1:
                    System.out.println("Lista de usuários: ");
                    usuarioServices.listarUsuarios();
                    break;
                case 2:
                    System.out.println("Digite o ID do usuário para exclusão: ");

                    Integer idUsuario = input.nextInt();

                    usuarioServices.remover(idUsuario);
                    break;
                case 3:
                    System.out.println(VOLTANDO);
                    exibirMenuPrincipalAdmin();
                    break;
                case 4:
                    System.out.println(SAINDO_DO_SISTEMA_MSG);
                    exibirLoginMenu();
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
            }
        } while (opPainelAdministrativo != 3);
    }

    private void exibirMenuPrincipalAdmin() {
        System.out.println(CABECALHO_NOTIFICA_MSG);

        if (usuarioLogado != null) {
            int opMenuPrincLogado;

            do {
                System.out.println("1. Usuário");
                System.out.println("2. Denúncia");
                System.out.println("3. Feed");
                System.out.println("4. Estatística");
                System.out.println("5. Painel Administrativo");
                System.out.println("6. Voltar");
                System.out.println("7. Sair");
                System.out.println(ESCOLHA_OPCAO_MSG);
                opMenuPrincLogado = input.nextInt();
                switch (opMenuPrincLogado) {
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
                        System.out.println("Painel Administrativo");
                        exibirPainelAdministrativo();
                        break;
                    case 6:
                        System.out.println(VOLTANDO);
                        exibirLoginMenu();
                        break;
                    case 7:
                        System.out.println(SAINDO_DO_SISTEMA_MSG);
                        exibirLoginMenu();
                        break;
                    default:
                        System.out.println(OPCAO_INVALIDA_MSG);
                }
            } while (opMenuPrincLogado != 7);
        } else {
            int opMenuPrincNLogado;

            do {
                System.out.println("1. Feed");
                System.out.println("2. Estatística");
                System.out.println("3. Voltar");
                System.out.println("4. Sair");
                System.out.println(ESCOLHA_OPCAO_MSG);

                opMenuPrincNLogado = input.nextInt();

                switch (opMenuPrincNLogado) {
                    case 1:
                        System.out.println("1. Feed");
                        break;
                    case 2:
                        System.out.println("2. Estatística");
                        break;
                    case 3:
                        exibirLoginMenu();
                        System.out.println(VOLTANDO);
                        break;
                    case 4:
                        System.out.println(SAINDO_DO_SISTEMA_MSG);
                        break;
                    default:
                        System.out.println(OPCAO_INVALIDA_MSG);
                }
            } while (opMenuPrincNLogado != 4);
        }
    }

    private void exibirMenuUsuario() {
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

            switch (opMenuUsuario) {
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
                    System.out.println(VOLTANDO);
                    exibirMenuPrincipal();
                    break;
                case 5:
                    System.out.println(SAINDO_DO_SISTEMA_MSG);
                    exibirLoginMenu();
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
            }
        } while (opMenuUsuario != 5);
    }

    private void exibirMenuDenuncia() {
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

            switch (opMenuDenuncia) {
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
                    exibirLoginMenu();
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
            }
        } while (opMenuDenuncia != 5);
    }

    private Usuario fazerLogin() {
        System.out.print("Digite seu nome de usuário: ");
        String nomeUsuario = input.next();
        System.out.print("Digite sua senha: ");
        String senha = input.next();

        return usuarioServices.fazerLogin(nomeUsuario, senha);
    }
}
