package views;

import models.Usuario;
import services.impl.AdminServiceImpl;
import services.impl.HomeServiceImpl;
import services.impl.UsuarioServicesImpl;

import java.util.Scanner;

public class Home {
    HomeServiceImpl homeServiceImpl = new HomeServiceImpl();
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
                            this.exibirMenuAdmin();
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

    private void exibirMenuAdmin() {
        AdminServiceImpl adminService = new AdminServiceImpl();

        int opMenuAdmin;
        System.out.println(CABECALHO_NOTIFICA_MSG);
        do {
            System.out.println("----- ADMIN -----");
            System.out.println("1. Ver usuários");
            System.out.println("2. Excluir usuários");
            System.out.println("3. Ver Denuncias");
            System.out.println("4. Excluir Denuncias");
            System.out.println("5. Ver Feed");
            System.out.println("6. Ver Estatísticas");
            System.out.println("7. Sair");
            System.out.println(ESCOLHA_OPCAO_MSG);
            opMenuAdmin = input.nextInt();
            switch (opMenuAdmin) {
                case 1:
                    System.out.println("1. Ver usuários");
                    adminService.listarUsuarios(usuarioLogado);
                    break;
                case 2:
                    //Lógica funcional, falta criar o método listas Usuarios e chamar aki tmb, mostrando id deles
                    System.out.println("Digite o Id do Usuário que Deseja Remover: ");
                    int idUsuario = input.nextInt();
                    usuarioServices.remover(idUsuario);
                    break;
                case 3:
                    System.out.println("3. Ver Denuncias");
                    adminService.listarDenuncias(usuarioLogado);
                    break;
                case 4:
                    System.out.println("4. Excluir Denuncias");
                    break;
                case 5:
                    System.out.println("5. Ver Feed");
                    break;
                case 6:
                    estatisticas();
                    break;
                case 7:
                    System.out.println(SAINDO_DO_SISTEMA_MSG);
                    exibirLoginMenu();
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
            }
        } while (opMenuAdmin != 8);
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
                System.out.println("5. Sair");
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
                        this.homeServiceImpl.feed();
                        break;
                    case 4:
                        estatisticas();
                        break;
                    case 5:
                        exibirLoginMenu();
                        System.out.println(VOLTANDO);
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
                        this.homeServiceImpl.feed();
                        break;
                    case 2:
                        estatisticas();
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
                    //funcionando FINALIZADO - NÃO MECHER
                    usuarioServices.remover(usuarioLogado.getIdUsuario());
                    break;
                case 2:
                    System.out.println("2. Editar Conta");
                    break;
                case 3:
                    //funcionando FINALIZADO - NÃO MECHER
                    usuarioServices.listarUsuario(usuarioLogado.getIdUsuario());
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

    private void estatisticas(){
        usuarioServices.exibirEstatisticasUsuarios();
    }

    private Usuario fazerLogin() {
        System.out.print("Digite seu nome de usuário: ");
        String nomeUsuario = input.next();
        System.out.print("Digite sua senha: ");
        String senha = input.next();

        return usuarioServices.fazerLogin(nomeUsuario, senha);
    }
}
