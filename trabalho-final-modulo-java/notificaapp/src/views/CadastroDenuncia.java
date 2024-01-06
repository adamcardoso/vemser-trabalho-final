package views;

import entities.Denuncia;
import entities.Localizacao;
import entities.Usuario;
import entities.enums.Categoria;
import entities.enums.Situacao;
import exceptions.InvalidInputException;
import interfaces.IDenunciaCadastro;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CadastroDenuncia implements IDenunciaCadastro {
    Scanner scanner = new Scanner(System.in);

    private static final Pattern INTEGER_PATTERN = Pattern.compile("^-?\\d+$");
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("^-?\\d*\\.\\d+$" + "|" + "^-?\\d+$");


    private String digitarNomeUsuario(){
        String nomeUsuario = null;
        int tentativas = 0;

        while (tentativas < 3){
            tentativas++;
            nomeUsuario = scanner.nextLine();

            if (nomeUsuario.isEmpty()) {
                System.err.println("Nome do usuário não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            } else {
                break;
            }
            if (tentativas == 3) {
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        return nomeUsuario;
    }

    private String digitarDescricao(){
        String descricao = null;
        int tentativas = 0;

        while (tentativas < 3){
            tentativas++;
            descricao = scanner.nextLine();

            if (descricao.isEmpty()) {
                System.err.println("Descrição não pode ser vazia.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            } else {
                break;
            }
            if (tentativas == 3) {
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        return descricao;
    }

    private double digitarLongitude(){
        String longitude = null;
        int tentativas = 0;

        while (tentativas < 3){
            tentativas++;

            longitude = scanner.nextLine();

            if (longitude.isEmpty()) {
                System.err.println("Longitude não pode ser vazia.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            } else if (!DOUBLE_PATTERN.matcher(longitude).find()) {
                System.err.println("Longitude deve ser um valor numérico.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            } else {
                break;
            }
            if (tentativas == 3) {
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        return Double.parseDouble(longitude);
    }

    private double digitarLatitude(){
        String latitude = null;
        int tentativas = 0;

        while (tentativas < 3){
            tentativas++;

            latitude = scanner.nextLine();

            if (latitude.isEmpty()) {
                System.err.println("Latitude não pode ser vazia.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            } else if (!DOUBLE_PATTERN.matcher(latitude).find()) {
                System.err.println("Latitude deve ser um valor numérico.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            } else {
                break;
            }
            if (tentativas == 3) {
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        return Double.parseDouble(latitude);
    }

    private int digitarStatus(){
        String status = null;
        int tentativas = 0;

        while (tentativas < 3){
            tentativas++;

            status = scanner.nextLine();

            if (status.isEmpty()) {
                System.err.println("Status não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            } else if (!INTEGER_PATTERN.matcher(status).find()) {
                System.err.println("Status deve ser um valor numérico.\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else if (Integer.parseInt(status) < 0 || Integer.parseInt(status) >= Situacao.values().length) {
                System.err.println("Status inválido.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            } else {
                break;
            }
            if (tentativas == 3) {
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        return Integer.parseInt(status);
    }

    private int digitarCategoria(){
        String categoria = null;
        int tentativas = 0;
        while (tentativas < 3){
            tentativas++;

            categoria = scanner.nextLine();

            if (categoria.isEmpty()) {
                System.err.println("Categoria não pode ser vazia.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            } else if (!INTEGER_PATTERN.matcher(categoria).find()) {
                System.err.println("Categoria deve ser um valor numérico.\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else if (Integer.parseInt(categoria) < 0 || Integer.parseInt(categoria) >= Categoria.values().length) {
                System.err.println("Categoria inválida.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            } else {
                break;
            }
            if (tentativas == 3) {
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        return Integer.parseInt(categoria);
    }

    @Override
    public Denuncia cadastrarDenuncia() {
        try {
            int idDenuncia = (int) (Math.random() * 1000);

            System.out.println("Digite o nome do usuário:");
            String nomeUsuario = this.digitarNomeUsuario();

            Usuario usuario = new Usuario(nomeUsuario);

            System.out.println("Digite a Descrição:");
            String descricao = this.digitarDescricao();

            System.out.println("Digite a Localização (Longitude):");
            double longitude = this.digitarLongitude();

            System.out.println("Digite a Localização (Latitude):");
            double latitude = this.digitarLatitude();

            Localizacao localizacao = new Localizacao(latitude, longitude);

            System.out.println("Selecione o Status da Denúncia:");
            for (Situacao situacao : Situacao.values()) {
                System.out.println(situacao.ordinal() + " - " + situacao);
            }

            int opcaoStatus = this.digitarStatus();
            Situacao statusDenuncia = Situacao.values()[opcaoStatus];

            System.out.println("Selecione a Categoria da Denúncia:");
            for (Categoria categoria : Categoria.values()) {
                System.out.println(categoria.ordinal() + " - " + categoria);
            }

            int opcaoCategoria = this.digitarCategoria();
            Categoria categoriaSelecionada = Categoria.values()[opcaoCategoria];

            LocalDateTime dataHora = LocalDateTime.now();

            Denuncia denuncia = new Denuncia(idDenuncia, descricao, localizacao, usuario, dataHora, statusDenuncia, categoriaSelecionada);

            return denuncia;
        } catch (InvalidInputException e) {
            System.err.println("Tipo de entrada inválida: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Denuncia editarDenuncia(int idDenuncia, Denuncia denuncia) {
        try {
            denuncia.setIdDenuncia(idDenuncia);

            System.out.println("Digite o nome do usuário:");
            String nomeUsuario = this.digitarNomeUsuario();

            Usuario usuario = new Usuario(nomeUsuario);
            denuncia.setUsuario(usuario);

            System.out.println("Digite a Descrição:");
            String descricao = this.digitarDescricao();
            denuncia.setDescricao(descricao);

            System.out.println("Digite a Localização (Longitude):");
            double longitude = this.digitarLongitude();

            System.out.println("Digite a Localização (Latitude):");
            double latitude = this.digitarLatitude();

            Localizacao localizacao = new Localizacao(latitude, longitude);
            denuncia.setLocal(localizacao);

            System.out.println("Selecione o Status da Denúncia:");
            for (Situacao situacao : Situacao.values()) {
                System.out.println(situacao.ordinal() + " - " + situacao);
            }

            int opcaoStatus = this.digitarStatus();
            Situacao statusDenuncia = Situacao.values()[opcaoStatus];
            denuncia.setStatusDenuncia(statusDenuncia);

            System.out.println("Selecione a Categoria da Denúncia:");
            for (Categoria categoria : Categoria.values()) {
                System.out.println(categoria.ordinal() + " - " + categoria);
            }

            int opcaoCategoria = this.digitarCategoria();
            Categoria categoriaSelecionada = Categoria.values()[opcaoCategoria];
            denuncia.setCategoria(categoriaSelecionada);

            LocalDateTime dataHora = LocalDateTime.now();

            denuncia.setDataHora(dataHora);
        } catch (InvalidInputException e) {
            System.err.println("Tipo de entrada inválida: " + e.getMessage());
            return null;
        }

        return denuncia;
    }

    @Override
    public void excluirDenuncia(int idDenuncia, HashMap<Integer, Denuncia> denuncias) {
        denuncias.remove(idDenuncia);
    }

    @Override
    public void visualizarDenuncia(Denuncia denuncia) {
        System.out.println("ID da Denúncia: " + denuncia.getIdDenuncia());
        System.out.println("Nome do Usuário: " + denuncia.getUsuario().getNomeUsuario());
        System.out.println("Descrição: " + denuncia.getDescricao());
        System.out.println("Localização (Longitude): " + denuncia.getLocal().getLongitude());
        System.out.println("Localização (Latitude): " + denuncia.getLocal().getLatitute());
        System.out.println("Status da Denúncia: " + denuncia.getStatusDenuncia());
        System.out.println("Categoria: " + denuncia.getCategoria());
        System.out.println("Data e Hora: " + denuncia.getDataHora());
        System.out.println("--------------------------------------");
    }
}
