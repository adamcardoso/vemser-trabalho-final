package services.impl;

import models.Denuncia;
import models.Usuario;
import models.enums.Categoria;
import models.enums.TipoDenuncia;
import services.interfaces.HomeService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class HomeServiceImpl implements HomeService {
    @Override
    public void feed(){
        DenunciaServicesImpl denunciaServices = new DenunciaServicesImpl();
        List<Denuncia> denuncias = denunciaServices.obterTodos();
        System.out.print("\n");
        for(Denuncia d: denuncias)
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
    public void denunciaForm(Usuario u, Scanner input){
        String titulo = "";
        String descricao = "";
        int categoria = 0;
        int tipoDenuncia = 0;

        DenunciaServicesImpl denunciaServices = new DenunciaServicesImpl();
        System.out.println("║ Você escolheu '1 - Cadastrar Denúncia'");
        do {
            System.out.println();
            System.out.println("║ Digite o título da denúncia: ");
            titulo = input.nextLine();
        } while(titulo.isEmpty());

        do {
            System.out.println();
            System.out.println("║ Digite uma descrição para a denúncia (pode ser breve): ");
            descricao = input.nextLine();
        } while(descricao.isEmpty());

        do {
            try{
                System.out.println();
                System.out.println("║ Em qual categoria estaria a sua denúncia: ");
                System.out.printf("╔═════════ NOTIFICA ════════╗%n");
                System.out.printf("║ 1. Água Potável           ║%n");
                System.out.printf("║ 2. Saneamento Básico      ║%n");
                System.out.printf("║ 3. Gestão de Resíduos     ║%n");
                System.out.printf("║ 4. Educação e Higiene     ║%n");
                System.out.printf("╚═══════════════════════════╝%n");
                categoria = input.nextInt();

                if(categoria < 1 || categoria > 4)
                    System.out.printf("""
                            
                            ║ Opção não existente.
                            """);
            } catch (InputMismatchException e){
                System.out.println();
                System.out.println("║ Houve um erro de digitação. Tente novamente.");
                input.nextLine();
            } catch (Exception e){
                System.out.println();
                System.out.println("║ Houve um erro imprevisto. Tente novamente.");
                input.nextLine();
            }

        } while(categoria < 1 || categoria > 4);

        do {
            try{
                System.out.println();
                System.out.println("║ Você deseja que sua denúncia seja pública ou anônima: ");
                System.out.printf("╔═════════ NOTIFICA ════════╗%n");
                System.out.printf("║ 1. Pública                ║%n");
                System.out.printf("║ 2. Anônima                ║%n");
                System.out.printf("╚═══════════════════════════╝%n");
                tipoDenuncia = input.nextInt();

                if(tipoDenuncia != 1 && tipoDenuncia != 2)
                    System.out.printf("""
                            
                            ║ Opção não existente.
                            """);

            } catch (InputMismatchException e){
                System.out.println();
                System.out.println("║ Houve um erro de digitação. Tente novamente.");
                input.nextLine();
            } catch (Exception e){
                System.out.println();
                System.out.println("║ Houve um erro imprevisto. Tente novamente.");
                input.nextLine();
            }
        } while(tipoDenuncia != 1 && tipoDenuncia != 2);

        Optional<Denuncia> dOpt = denunciaServices.adicionarDenuncia(
                new Denuncia(
                        titulo, descricao, Categoria.fromInt(categoria),
                        TipoDenuncia.fromInt(tipoDenuncia), u.getIdUsuario()));

        if(dOpt.isEmpty())
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
}
