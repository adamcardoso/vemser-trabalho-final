package views;

import helpers.CadastroDenunciaHelper;
import models.Denuncia;
import models.Usuario;
import models.enums.Categoria;
import services.impl.*;
import services.interfaces.HomeService;

import java.util.Scanner;

public class Home {
    HomeService homeServiceImpl = new HomeServiceImpl();
    EstatisticaService estatisticaService = new EstatisticaService();
    UsuarioServicesImpl usuarioServices = new UsuarioServicesImpl();
    DenunciaServicesImpl denunciaServices = new DenunciaServicesImpl();

    CadastroDenunciaHelper cadastroDenunciaHelper = new CadastroDenunciaHelper();
    Scanner input = new Scanner(System.in);

    private static final String OPCAO_INVALIDA_MSG = "║ Opção inválida!";
    private static final String SAINDO_DO_SISTEMA_MSG = "║ Saindo do sistema...";
    private static final String ESCOLHA_OPCAO_MSG = "║ Escolha uma opção: ";
    private static final String VOLTANDO = "║ Voltando... ";
    private Usuario usuarioLogado = null;

    public void exibirLoginMenu() {
        int opMenuLogin;
        do {
            System.out.print("\n");
            System.out.printf("╔═════════ NOTIFICA ════════╗%n");
            System.out.printf("║ 1. Fazer login no sistema ║%n");
            System.out.printf("║ 2. Entrar sem login       ║%n");
            System.out.printf("║ 3. Cadastrar              ║%n");
            System.out.printf("║ 4. Sair                   ║%n");
            System.out.printf("╚═══════════════════════════╝%n");
            System.out.print(ESCOLHA_OPCAO_MSG);
            opMenuLogin = input.nextInt();
            input.nextLine();

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
                    usuarioServices.adicionar(usuarioLogado);
                    break;
                case 4:
                    System.out.println(SAINDO_DO_SISTEMA_MSG);
                    System.exit(0);
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
            }
        } while (opMenuLogin != 4);
    }

    private void exibirMenuAdmin() {
        AdminServiceImpl adminService = new AdminServiceImpl();

        int opMenuAdmin;
        do {
            System.out.print("\n");
            System.out.printf("╔══════════ ADMIN ═════════╗%n");
            System.out.printf("║1. Ver usuários           ║%n");
            System.out.printf("║2. Excluir usuários       ║%n");
            System.out.printf("║3. Ver Denuncias          ║%n");
            System.out.printf("║4. Excluir Denuncias      ║%n");
            System.out.printf("║5. Ver Feed               ║%n");
            System.out.printf("║6. Ver Estatísticas       ║%n");
            System.out.printf("║7. Sair                   ║%n");
            System.out.printf("╚══════════════════════════╝%n");
            System.out.print(ESCOLHA_OPCAO_MSG);
            opMenuAdmin = input.nextInt();
            input.nextLine();

            switch (opMenuAdmin) {
                case 1:
                    System.out.println("\n═══════ Lista de Todos Usuários ═══════");
                    adminService.listarUsuarios(usuarioLogado);
                    break;
                case 2:
                    System.out.println("Digite o Id do Usuário que Deseja Remover: ");
                    int idUsuario = input.nextInt();
                    input.nextLine();
                    usuarioServices.remover(idUsuario);
                    break;
                case 3:
                    adminService.listarDenuncias(usuarioLogado);
                    break;
                case 4:
                    System.out.println("Digite o Id da Denúncia que Deseja Remover: ");
                    int idDenuncia = input.nextInt();
                    input.nextLine();
                    denunciaServices.removerDenuncia(idDenuncia);
                    break;
                case 5:
                    this.homeServiceImpl.feed();
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
        } while (opMenuAdmin != 7);
    }

    private void exibirMenuPrincipal() {
        if (usuarioLogado != null) {
            int opMenuPrincLogado;
            do {
                System.out.print("\n");
                System.out.printf("╔═══════ NOTIFICA ═════╗%n");
                System.out.printf("║ 1. Usuário           ║%n");
                System.out.printf("║ 2. Denúncia          ║%n");
                System.out.printf("║ 3. Feed              ║%n");
                System.out.printf("║ 4. Estatística       ║%n");
                System.out.printf("║ 5. Sair              ║%n");
                System.out.printf("╚══════════════════════╝%n");
                System.out.print(ESCOLHA_OPCAO_MSG);
                opMenuPrincLogado = input.nextInt();
                input.nextLine();
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
                        System.out.println(VOLTANDO);
                        exibirLoginMenu();
                        break;
                    default:
                        System.out.println(OPCAO_INVALIDA_MSG);
                }
            } while (opMenuPrincLogado != 5);
        } else {
            int opMenuPrincNLogado;
            do {
                System.out.print("\n");
                System.out.printf("╔══════ NOTIFICA ════╗%n");
                System.out.printf("║ 1. Feed            ║%n");
                System.out.printf("║ 2. Estatística     ║%n");
                System.out.printf("║ 3. Sair            ║%n");
                System.out.printf("╚════════════════════╝%n");
                System.out.print(ESCOLHA_OPCAO_MSG);
                opMenuPrincNLogado = input.nextInt();
                input.nextLine();
                switch (opMenuPrincNLogado) {
                    case 1:
                        this.homeServiceImpl.feed();
                        break;
                    case 2:
                        estatisticas();
                        break;
                    case 3:
                        System.out.println(VOLTANDO);
                        break;
                    default:
                        System.out.println(OPCAO_INVALIDA_MSG);
                }
            } while (opMenuPrincNLogado != 3);
        }
    }

    private void exibirMenuUsuario() {
        int opMenuUsuario;
        do {
            System.out.print("\n");
            System.out.printf("╔═══════ NOTIFICA ═════╗%n");
            System.out.printf("║ 1. Excluir Conta     ║%n");
            System.out.printf("║ 2. Editar Conta      ║%n");
            System.out.printf("║ 3. Visualizar Conta  ║%n");
            System.out.printf("║ 4. Voltar            ║%n");
            System.out.printf("║ 5. Sair              ║%n");
            System.out.printf("╚══════════════════════╝%n");
            System.out.print(ESCOLHA_OPCAO_MSG);
            opMenuUsuario = input.nextInt();
            input.nextLine();
            switch (opMenuUsuario) {
                case 1:
                    usuarioServices.remover(usuarioLogado.getIdUsuario());
                    break;
                case 2:
                    usuarioServices.editarUsuario(usuarioLogado.getIdUsuario(), usuarioLogado);
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
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
            }
        } while (opMenuUsuario != 5 && opMenuUsuario != 4);
    }

    private void exibirMenuDenuncia() {
        int opMenuDenuncia;

        do {
            System.out.print("\n");
            System.out.printf("╔═════════ NOTIFICA ═══════╗%n");
            System.out.printf("║ 1. Cadastrar Denuncia    ║%n");
            System.out.printf("║ 2. Excluir Denuncia      ║%n");
            System.out.printf("║ 3. Editar Denuncia       ║%n");
            System.out.printf("║ 4. Visualizar Denuncias  ║%n");
            System.out.printf("║ 5. Voltar                ║%n");
            System.out.printf("║ 6. Sair                  ║%n");
            System.out.printf("╚══════════════════════════╝%n");
            System.out.print(ESCOLHA_OPCAO_MSG);
            opMenuDenuncia = input.nextInt();
            input.nextLine();
            switch (opMenuDenuncia) {
                case 1:
                    homeServiceImpl.denunciaForm(this.usuarioLogado, input);
                    break;
                case 2:
                    System.out.println("Digite o Id da Denúncia que Deseja Remover: ");
                    int idDenunciaPessoal = input.nextInt();
                    input.nextLine();
                    denunciaServices.removerDenuncia(idDenunciaPessoal);
                    break;
                case 3:
                    System.out.println("Digite o Id da Denúncia que Deseja Editar: ");
                    int idDenunciaPessoalE = input.nextInt();
                    input.nextLine();
                    this.editarDenuncia(idDenunciaPessoalE);
                    break;
                case 4:
                    this.homeServiceImpl.listarDenunciasDoUsuario(this.usuarioLogado);
                    break;
                case 5:
                    exibirMenuPrincipal();
                    System.out.println(VOLTANDO);
                    break;
                case 6:
                    System.out.println(SAINDO_DO_SISTEMA_MSG);
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
            }
        } while (opMenuDenuncia != 5 && opMenuDenuncia != 6);
    }

    private void estatisticas() {
        estatisticaService.exibirEstatisticasUsuarios();
    }

    private Usuario fazerLogin() {
        System.out.println("\n═══════════════ Login ═══════════════");
        System.out.print("Digite seu nome de usuário: ");
        String nomeUsuario = input.next();
        input.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = input.next();
        input.nextLine();
        System.out.println("═════════════════════════════════════");
        return usuarioServices.fazerLogin(nomeUsuario, senha);
    }

    private void editarDenuncia(int idDenuncia) {
        int opMenuDenuncia;
        do {
            Denuncia editaDenuncia = denunciaServices.obterDenunciaPorId(idDenuncia, usuarioLogado.getIdUsuario());

            System.out.print("\n");
            System.out.printf("╔═════════ NOTIFICA ════════╗%n");
            System.out.printf("║ Qual campo deseja editar? ║%n");
            System.out.printf("║ 1. Titulo                 ║%n");
            System.out.printf("║ 2. Descricao              ║%n");
            System.out.printf("║ 3. Categoria              ║%n");
            System.out.printf("║ 4. Voltar                 ║%n");
            System.out.printf("╚═══════════════════════════╝%n");
            System.out.print(ESCOLHA_OPCAO_MSG);
            opMenuDenuncia = input.nextInt();
            input.nextLine();
            switch (opMenuDenuncia) {
                case 1:
                    String editaTitulo = cadastroDenunciaHelper.digitarTitulo();
                    editaDenuncia.setTitulo(editaTitulo);
                    denunciaServices.editarDenuncia(idDenuncia, usuarioLogado.getIdUsuario(), editaDenuncia);
                    break;
                case 2:
                    String editaDescricao = cadastroDenunciaHelper.digitarDescricao();
                    editaDenuncia.setDescricao(editaDescricao);
                    denunciaServices.editarDenuncia(idDenuncia, usuarioLogado.getIdUsuario(), editaDenuncia);
                    break;
                case 3:
                    Categoria editaCategoria = Categoria.fromInt(cadastroDenunciaHelper.digitarCategoria());
                    editaDenuncia.setCategoria(editaCategoria);
                    denunciaServices.editarDenuncia(idDenuncia, usuarioLogado.getIdUsuario(), editaDenuncia);
                    break;
                case 4:
                    System.out.println(VOLTANDO);
                    break;
                default:
                    System.out.println(OPCAO_INVALIDA_MSG);
            }
        } while (opMenuDenuncia != 4);
    }
}
