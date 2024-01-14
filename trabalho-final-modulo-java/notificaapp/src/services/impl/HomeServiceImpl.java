package services.impl;

import helpers.CadastroUsuarioHelper;
import models.Denuncia;
import models.Usuario;
import models.enums.Categoria;
import models.enums.TipoDenuncia;
import services.interfaces.DenunciaService;
import services.interfaces.HomeService;
import services.interfaces.UsuarioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class HomeServiceImpl implements HomeService {
    @Override
    public void feed() {
        DenunciaServicesImpl denunciaServices = new DenunciaServicesImpl();
        DenunciaService denunciaServices = new DenunciaServicesImpl();
        List<Denuncia> denuncias = denunciaServices.obterTodos();
        System.out.print("\n");
        for (Denuncia d : denuncias)
            System.out.printf("""
                    ╔═════════════════════════════════════════════════════════════════════════════╗
                    ║ id: %s
                    ║ titulo: %s
                    ║ descrição: %s
                    ║ status: %s
                    ╚═════════════════════════════════════════════════════════════════════════════╝
                    %n""", d.getIdDenuncia(), d.getTitulo(), d.getDescricao(), d.getStatusDenuncia());
    }

    @Override
    public void denunciaForm(Usuario u, Scanner input) {
        String titulo = "";
        String descricao = "";
        int categoria = 0;
        int tipoDenuncia = 0;

        DenunciaService denunciaServices = new DenunciaServicesImpl();
        System.out.println("║ Você escolheu '1 - Cadastrar Denúncia'");
        do {
            System.out.println();
            System.out.println("║ Digite o título da denúncia: ");
            titulo = input.nextLine();
        } while (titulo.isEmpty());

        do {
            System.out.println();
            System.out.println("║ Digite uma descrição para a denúncia (pode ser breve): ");
            descricao = input.nextLine();
        } while (descricao.isEmpty());

        do {
            try {
                System.out.println();
                System.out.println("║ Em qual categoria estaria a sua denúncia: ");
                System.out.printf("╔═════════ NOTIFICA ════════╗%n");
                System.out.printf("║ 1. Água Potável           ║%n");
                System.out.printf("║ 2. Saneamento Básico      ║%n");
                System.out.printf("║ 3. Gestão de Resíduos     ║%n");
                System.out.printf("║ 4. Educação e Higiene     ║%n");
                System.out.printf("╚═══════════════════════════╝%n");
                categoria = input.nextInt();

                if (categoria < 1 || categoria > 4)
                    System.out.printf("""
                                                        
                            ║ Opção não existente.
                            """);
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("║ Houve um erro de digitação. Tente novamente.");
                input.nextLine();
            } catch (Exception e) {
                System.out.println();
                System.out.println("║ Houve um erro imprevisto. Tente novamente.");
                input.nextLine();
            }

        } while (categoria < 1 || categoria > 4);

        do {
            try {
                System.out.println();
                System.out.println("║ Você deseja que sua denúncia seja pública ou anônima: ");
                System.out.printf("╔═════════ NOTIFICA ════════╗%n");
                System.out.printf("║ 1. Pública                ║%n");
                System.out.printf("║ 2. Anônima                ║%n");
                System.out.printf("╚═══════════════════════════╝%n");
                tipoDenuncia = input.nextInt();

                if (tipoDenuncia != 1 && tipoDenuncia != 2)
                    System.out.printf("""
                                                        
                            ║ Opção não existente.
                            """);

            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("║ Houve um erro de digitação. Tente novamente.");
                input.nextLine();
            } catch (Exception e) {
                System.out.println();
                System.out.println("║ Houve um erro imprevisto. Tente novamente.");
                input.nextLine();
            }
        } while (tipoDenuncia != 1 && tipoDenuncia != 2);

        Optional<Denuncia> dOpt = denunciaServices.adicionarDenuncia(
                new Denuncia(
                        titulo, descricao, Categoria.fromInt(categoria),
                        TipoDenuncia.fromInt(tipoDenuncia), u.getIdUsuario()));

        if (dOpt.isEmpty())
            System.out.printf("""
                    ╔═════════ NOTIFICA ══════════════════════════════════════════════════════════╗
                    ║ Ocorreu um Erro ao cadastrar a denúncia, tente novamente.                   ║
                    ╚═════════════════════════════════════════════════════════════════════════════╝
                    %n""");
        else
            System.out.printf("""
                    ╔═════════ NOTIFICA ══════════════════════════════════════════════════════════╗
                    ║ Sua denúncia foi processada corretamente!.                                  ║
                    ╚═════════════════════════════════════════════════════════════════════════════╝
                    %n""");
    }

    @Override
    public void cadastroUsuarioForm(Scanner input) {
        CadastroUsuarioHelper cadastroUsuarioHelper = new CadastroUsuarioHelper();

        String nome = "";
        String celular = "";
        String senha = "";
        String data = "";
        int etnia = -1;
        int classeSocial = -1;
        int genero = -1;

        UsuarioService usuarioService = new UsuarioServicesImpl();
        System.out.println("║ Você escolheu '3 - Cadastrar Usuario'");
        do {
            System.out.println();
            System.out.println("║ Digite seu nome: ");
            nome = input.nextLine();
        } while (nome.isEmpty());

        do {
            System.out.println();
            System.out.println("║ Digite seu numero de celular: ");
            celular = input.nextLine();
        } while (celular.length() > 11 || celular.length() < 10);

        do {
            System.out.println();
            System.out.println("║ Digite sua senha: ");
            senha = input.nextLine();
        } while (senha.isEmpty());

        do {
            System.out.println();
            System.out.println("║ Digite sua data(dd-MM-yyyy): ");
            data = input.nextLine();
        } while (data.isEmpty());

        do {
            try {
                System.out.println();
                System.out.println("║ Qual sua etnia? ");
                System.out.printf("╔═════════ NOTIFICA ════════╗%n");
                System.out.printf("║ 1. PRETO                  ║%n");
                System.out.printf("║ 2. PARDO                  ║%n");
                System.out.printf("║ 3. BRANCO                 ║%n");
                System.out.printf("║ 4. INDIGENA               ║%n");
                System.out.printf("║ 5. AMARELO                ║%n");
                System.out.printf("╚═══════════════════════════╝%n");
                etnia = input.nextInt() - 1;

                if (etnia < 0 || etnia > 4)
                    System.out.printf("""
                            ║ Opção não existente.
                            """);
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("║ Houve um erro de digitação. Tente novamente.");
                input.nextLine();
            } catch (Exception e) {
                System.out.println();
                System.out.println("║ Houve um erro imprevisto. Tente novamente.");
                input.nextLine();
            }

        } while (etnia < 0 || etnia > 4);

        do {
            try {
                System.out.println();
                System.out.println("║ Qual sua classeSocial? ");
                System.out.printf("╔═════════ NOTIFICA ════════╗%n");
                System.out.printf("║ 1. A                      ║%n");
                System.out.printf("║ 2. B                      ║%n");
                System.out.printf("║ 3. C                      ║%n");
                System.out.printf("║ 4. D                      ║%n");
                System.out.printf("║ 5. E                      ║%n");
                System.out.printf("╚═══════════════════════════╝%n");
                classeSocial = input.nextInt() - 1;

                if (classeSocial < 0 || classeSocial > 4)
                    System.out.printf("""
                            ║ Opção não existente.
                            """);
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("║ Houve um erro de digitação. Tente novamente.");
                input.nextLine();
            } catch (Exception e) {
                System.out.println();
                System.out.println("║ Houve um erro imprevisto. Tente novamente.");
                input.nextLine();
            }

        } while (classeSocial < 0 || classeSocial > 4);


        do {
            try {
                System.out.println();
                System.out.println("║ Qual seu genero ?  ");
                System.out.printf("╔═════════ NOTIFICA ════════╗%n");
                System.out.printf("║ 1. MASCULINO              ║%n");
                System.out.printf("║ 2. FEMININO               ║%n");
                System.out.printf("║ 3. OUTRO                  ║%n");
                System.out.printf("╚═══════════════════════════╝%n");
                genero = input.nextInt() - 1;

                if (genero < 0 || genero > 2)
                    System.out.printf("""
                            ║ Opção não existente.
                            """);
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("║ Houve um erro de digitação. Tente novamente.");
                input.nextLine();
            } catch (Exception e) {
                System.out.println();
                System.out.println("║ Houve um erro imprevisto. Tente novamente.");
                input.nextLine();
            }

        } while (genero < 0 || genero > 2);


        Optional<Usuario> uOpt = usuarioService.adicionar(
                new Usuario(
                        nome, celular, senha,
                        LocalDate.parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        Etnia.fromInt(etnia),
                        ClasseSocial.fromInt(classeSocial),
                        Genero.fromInt(genero)));
        if (uOpt.isEmpty())
            System.out.printf("""
                    ╔═════════ NOTIFICA ══════════════════════════════════════════════════════════╗
                    ║ Ocorreu um Erro ao cadastrar seu Usuario, tente novamente.                   ║
                    ╚═════════════════════════════════════════════════════════════════════════════╝
                    %n""");
        else
            System.out.printf("""
                    ╔═════════ NOTIFICA ══════════════════════════════════════════════════════════╗
                    ║ Seu Usuario foi cadastrado corretamente!.                                   ║
                    ╚═════════════════════════════════════════════════════════════════════════════╝
                    %n""");
    }

    @Override
    public void listarDenunciasDoUsuario(Usuario usuario){
        DenunciaService denunciaService = new DenunciaServicesImpl();
        Optional<List<Denuncia>> dOpt = denunciaService.listarDenunciasDoUsuario(usuario.getIdUsuario());

        if(dOpt.isEmpty())
            System.out.printf("""
                        ╔═════════ NOTIFICA ══════════════════════════════════════════════════════════╗
                        ║ Ocorreu um Erro ao cadastrar a denúncia, tente novamente.                   ║
                        ╚═════════════════════════════════════════════════════════════════════════════╝
                        %n""");
        else {
            List<Denuncia> denuncias = dOpt.get();

            for (Denuncia d : denuncias)
                System.out.printf("""
                        ╔═════════ NOTIFICA ══════════════════════════════════════════════════════════╗
                        ║ id: %s
                        ║ título: %s
                        ║ descrição: %s
                        ║ status: %s
                        ╚═════════════════════════════════════════════════════════════════════════════╝
                        %n""", d.getIdDenuncia(), d.getTitulo(),
                        this.substring(d.getDescricao()), d.getStatusDenuncia());
        }
    }

    private String substring(String str){
        return str.length() > 60 ? str.substring(0, 60) + "..." : str;
    }
}
