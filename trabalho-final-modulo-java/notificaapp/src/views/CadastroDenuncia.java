package views;

import entities.Denuncia;
import entities.Localizacao;
import entities.Usuario;
import entities.enums.Categoria;
import entities.enums.Situacao;
import exceptions.InvalidInputException;
import interfaces.IDenunciaCadastro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class CadastroDenuncia implements IDenunciaCadastro {
    Scanner scanner = new Scanner(System.in);
    @Override
    public Denuncia cadastrarDenuncia() {
        Denuncia denuncia = new Denuncia();

        try {
            int idDenuncia = (int) (Math.random() * 1000);

            denuncia.setIdDenuncia(idDenuncia);

            System.out.println("Digite o nome do usuário:");

            String nomeUsuario = scanner.nextLine();

            if (nomeUsuario.isEmpty()) {
                throw new InvalidInputException("Nome do usuário não pode ser vazio.");
            }

            Usuario usuario = new Usuario();

            usuario.setNomeUsuario(nomeUsuario);

            denuncia.setUsuario(usuario);

            System.out.println("Digite a Descrição:");

            String descricao = scanner.nextLine();

            denuncia.setDescricao(descricao);

            System.out.println("Digite a Localização (Longitude):");

            double longitude = scanner.nextDouble();

            System.out.println("Digite a Localização (Latitude):");

            double latitude = scanner.nextDouble();

            Localizacao localizacao = new Localizacao();

            localizacao.setLongitude(longitude);

            localizacao.setLatitute(latitude);

            denuncia.setLocal(localizacao);

            System.out.println("Selecione o Status da Denúncia:");

            for (Situacao situacao : Situacao.values()) {
                System.out.println(situacao.ordinal() + " - " + situacao);
            }

            int opcaoStatus = scanner.nextInt();

            Situacao statusDenuncia = Situacao.values()[opcaoStatus];

            denuncia.setStatusDenuncia(statusDenuncia);

            System.out.println("Selecione a Categoria da Denúncia:");

            for (Categoria categoria : Categoria.values()) {
                System.out.println(categoria.ordinal() + " - " + categoria);
            }

            int opcaoCategoria = scanner.nextInt();

            Categoria categoriaSelecionada = Categoria.values()[opcaoCategoria];

            denuncia.setCategoria(categoriaSelecionada);

            LocalDateTime dataHora = LocalDateTime.now();

            denuncia.setDataHora(dataHora);
        } catch (InvalidInputException e) {
            System.out.println("Tipo de entrada inválida: " + e.getMessage());
        }

        return denuncia;
    }

    @Override
    public Denuncia editarDenuncia(int idDenuncia) {
        //ADICIONAR LÓGICA DE EDITAR
        return null;
    }

    @Override
    public int excluirDenuncia(ArrayList<Denuncia> denuncia) {
        //ADICIONAR LÓGICA DE EXCLUSÃO
        return 0;
    }

    @Override
    public void visualizarDenuncia(Denuncia denuncia) {
        System.out.println("ID da Denúncia: " + denuncia.getIdDenuncia());
        System.out.println("Nome do Usuário: " + denuncia.getUsuario().getNomeUsuario());
        System.out.println("Descrição: " + denuncia.getDescricao());
        System.out.println("Localização: Latitude - " + denuncia.getLocal().getLatitute() +
                ", Longitude - " + denuncia.getLocal().getLongitude());
        System.out.println("Status da Denúncia: " + denuncia.getStatusDenuncia());
        System.out.println("Categoria: " + denuncia.getCategoria());
        System.out.println("Data e Hora: " + denuncia.getDataHora());
        System.out.println("--------------------------------------");

    }

}