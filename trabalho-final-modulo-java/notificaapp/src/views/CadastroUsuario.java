package views;

import entities.Denuncia;
import entities.Usuario;
import entities.enums.TipoUsuario;
import exceptions.InvalidInputException;
import interfaces.IUsuarioCadastro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
public class CadastroUsuario implements IUsuarioCadastro{
    Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    @Override
    public Usuario cadastrarUsuario() {

        Usuario usuario = new Usuario();
        try {
            int idUsuario = random.nextInt(1000);
            usuario.setIdUsuario(idUsuario);

            System.out.println("Digite o nome do usuário:");
            String nomeUsuario = scanner.nextLine();
            if (nomeUsuario.isEmpty()) {
                throw new InvalidInputException("Nome do usuário não pode ser vazio.");
            }

            usuario.setNomeUsuario(nomeUsuario);

            System.out.println("Digite o número do celular:");
            String numeroCelular = scanner.nextLine();
            if (!numeroCelular.matches("\\d{10}")) {
                throw new InvalidInputException("Número de celular inválido. Deve conter 10 dígitos numéricos.");
            }

            usuario.setNumeroCelular(numeroCelular);

            System.out.println("Digite a senha do usuário:");
            String senhaUsuario = scanner.nextLine();
            if (senhaUsuario.length() < 6) {
                throw new InvalidInputException("Senha deve conter pelo menos 6 caracteres.");
            }

            usuario.setSenhaUsuario(senhaUsuario);

            System.out.println("Digite a Etnia:");
            String etnia = scanner.nextLine();
            usuario.setEtniaUsuario(etnia);

            System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
            String data = scanner.nextLine();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false);

                Date dataNascimento = dateFormat.parse(data);
                usuario.setDataNascimento(dataNascimento);
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Certifique-se de usar o formato dd/MM/yyyy.");
            }

            System.out.println("Digite sua faixa salárial:");
            String classesocial = scanner.nextLine();
            usuario.setEtniaUsuario( classesocial);

            System.out.println("Digite o Genero:");
            String genero = scanner.nextLine();
            usuario.setEtniaUsuario(genero);


            System.out.println("Selecione o TipoUsuario:");
            for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
                System.out.println(tipoUsuario.ordinal() + " - " + tipoUsuario);
            }

            int opcaoTipo = scanner.nextInt();
            if (opcaoTipo < 0 || opcaoTipo >= TipoUsuario.values().length) {
                throw new InvalidInputException("Opção de tipo de usuário inválida.");
            }

            TipoUsuario tipo = TipoUsuario.values()[opcaoTipo];
            usuario.setTipoUsuario(tipo);
        } catch (InvalidInputException e) {
            System.out.println("Tipo de entrada inválida: " + e.getMessage());
        }

        return usuario;
    }

    @Override
    public Usuario editarUsuario(int idUsuario, Usuario usuario) {
        try {
            usuario.setIdUsuario(idUsuario);
            System.out.println("Digite o nome do usuário:");

            String nomeUsuario = scanner.nextLine();

            if (nomeUsuario.isEmpty()) {
                throw new InvalidInputException("Nome do usuário não pode ser vazio.");
            }

            usuario.setNomeUsuario(nomeUsuario);

            System.out.println("Digite o número do celular:");
            String numeroCelular = scanner.nextLine();
            if (!numeroCelular.matches("\\d{10}")) {
                throw new InvalidInputException("Número de celular inválido. Deve conter 10 dígitos numéricos.");
            }

            usuario.setNumeroCelular(numeroCelular);

            System.out.println("Digite a senha do usuário:");
            String senhaUsuario = scanner.nextLine();
            if (senhaUsuario.length() < 6) {
                throw new InvalidInputException("Senha deve conter pelo menos 6 caracteres.");
            }

            usuario.setSenhaUsuario(senhaUsuario);

            System.out.println("Digite a Etnia:");
            String etnia = scanner.nextLine();
            usuario.setEtniaUsuario(etnia);

            System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
            String data = scanner.nextLine();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false);

                Date dataNascimento = dateFormat.parse(data);
                usuario.setDataNascimento(dataNascimento);
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Certifique-se de usar o formato dd/MM/yyyy.");
            }

            System.out.println("Digite sua faixa salárial:");
            String classesocial = scanner.nextLine();
            usuario.setEtniaUsuario(classesocial);

            System.out.println("Digite o Genero:");
            String genero = scanner.nextLine();
            usuario.setEtniaUsuario(genero);

            System.out.println("Selecione o TipoUsuario:");
            for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
                System.out.println(tipoUsuario.ordinal() + " - " + tipoUsuario);
            }

            int opcaoTipo = scanner.nextInt();
            if (opcaoTipo < 0 || opcaoTipo >= TipoUsuario.values().length) {
                throw new InvalidInputException("Opção de tipo de usuário inválida.");
            }

            TipoUsuario tipo = TipoUsuario.values()[opcaoTipo];
            usuario.setTipoUsuario(tipo);
        } catch (InvalidInputException e) {
            System.out.println("Tipo de entrada inválida: " + e.getMessage());
        }

        return usuario;
    }



    @Override
    public void excluirUsuario(int idUsuario, HashMap<Integer, Usuario> usuario) {


        if (usuario.containsKey(idUsuario)){
            usuario.remove(idUsuario);
        } else
            System.out.println("Usuario não encontrado");
    }

    @Override
    public void visualizarUsuario(Usuario usuario) {
        System.out.println("ID do Usuário: " + usuario.getIdUsuario());
        System.out.println("Nome do Usuário: " + usuario.getNomeUsuario());
        System.out.println("Número do Celular: " + usuario.getNumeroCelular());
        System.out.println("Senha do Usuário: " + usuario.getSenhaUsuario());
        System.out.println("Etnia do Usuário: " + usuario.getEtniaUsuario());
        System.out.println("Data Nascimento: " + usuario.getDataNascimento());
        System.out.println("Classe Social: " + usuario.getClasseSocial());
        System.out.println("Genero: " + usuario.getGeneroUsuario());
        System.out.println("Tipo do Usuário: " + usuario.getTipoUsuario());


    }

}
