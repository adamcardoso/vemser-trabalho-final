package views;

import entities.Usuario;
import entities.enums.TipoUsuario;
import exceptions.InvalidInputException;
import interfaces.IUsuarioCadastro;

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
    public Usuario editarUsuario(int idUsuario) {
        //ADICIONAR LÓGICA
        return null;
    }

    @Override
    public void excluirUsuario(Usuario usuarioLogado) {
        //ADICIONAR LÓGICA
    }

    @Override
    public void visualizarUsuario(Usuario usuario) {
        System.out.println("ID do Usuário: " + usuario.getIdUsuario());
        System.out.println("Nome do Usuário: " + usuario.getNomeUsuario());
        System.out.println("Número do Celular: " + usuario.getNumeroCelular());
        System.out.println("Senha do Usuário: " + usuario.getSenhaUsuario());
        System.out.println("Etnia do Usuário: " + usuario.getEtniaUsuario());
        System.out.println("Tipo do Usuário: " + usuario.getTipoUsuario());
    }

}
