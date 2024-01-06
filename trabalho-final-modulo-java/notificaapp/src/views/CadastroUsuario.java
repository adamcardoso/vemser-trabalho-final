package views;

import entities.Usuario;
import entities.enums.ClasseSocial;
import entities.enums.Etnia;
import entities.enums.Genero;
import entities.enums.TipoUsuario;
import exceptions.MaxAttemptsExceededException;
import helpers.CadastroUsuarioHelper;
import interfaces.IUsuarioCadastro;

import java.time.LocalDate;
import java.util.HashMap;

public class CadastroUsuario extends CadastroUsuarioHelper implements IUsuarioCadastro {

    @Override
    public Usuario cadastrarUsuario() {
        Usuario usuario = new Usuario();
        try {
            int idUsuario = gerarNumeroAleatorio(1000);
            usuario.setIdUsuario(idUsuario);

            System.out.println("Digite o nome do usuário:");
            String nomeUsuario = digitarNomeUsuario();
            usuario.setNomeUsuario(nomeUsuario);

            System.out.println("Digite o número do celular:");
            String numeroCelular = digitarCampoNumeroCelular();
            usuario.setNumeroCelular(numeroCelular);

            System.out.println("Digite a senha do usuário:");
            System.out.println("""
                    Mínimo 6 e máximo 18 caracteres
                    Pelo menos 1 número
                    Pelo menos 1 letra minúscula
                    Pelo menos 1 letra maiúscula
                    Pelo menos 1 caracter especial (@ # $ % &)
                    """);
            String senhaUsuario = digitarCampoSenha();
            usuario.setSenhaUsuario(senhaUsuario);

            System.out.println("Digite a Etnia:");
            Etnia etnia = digitarCampoEtnia();
            usuario.setEtniaUsuario(etnia);

            System.out.println("Digite sua data de nascimento (dd-MM-yyyy): ");
            LocalDate dataNascimento = digitarCampoData();
            usuario.setDataNascimento(dataNascimento);

            System.out.println("Digite sua faixa salarial:");
            ClasseSocial classeSocial = digitarCampoClasseSocial();
            usuario.setClasseSocial(classeSocial);

            System.out.println("Digite o Gênero:");
            Genero genero = digitarCampoGenero();
            usuario.setGeneroUsuario(genero);

            System.out.println("Selecione o Tipo de Usuário:");
            TipoUsuario tipo = digitarCampoTipoDeUsuario();
            usuario.setTipoUsuario(tipo);

        } catch (MaxAttemptsExceededException e) {
            System.out.println("Tipo de entrada inválida: " + e.getMessage());
        }

        return usuario;
    }

    @Override
    public Usuario editarUsuario(int idUsuario, Usuario usuario) {
        try {
            usuario.setIdUsuario(idUsuario);
            System.out.println("Digite o nome do usuário:");
            String nomeUsuario = digitarNomeUsuario();
            usuario.setNomeUsuario(nomeUsuario);

            System.out.println("Digite o número do celular:");
            String numeroCelular = digitarCampoNumeroCelular();
            usuario.setNumeroCelular(numeroCelular);

            System.out.println("Digite a senha do usuário:");
            System.out.println("""
                    Mínimo 6 e máximo 18 caracteres
                    Pelo menos 1 número
                    Pelo menos 1 letra minúscula
                    Pelo menos 1 letra maiúscula
                    Pelo menos 1 caracter especial (@ # $ % &)
                    """);
            String senhaUsuario = digitarCampoSenha();
            usuario.setSenhaUsuario(senhaUsuario);

            System.out.println("Digite a Etnia:");
            Etnia etnia = digitarCampoEtnia();
            usuario.setEtniaUsuario(etnia);

            System.out.println("Digite sua data de nascimento (dd-MM-yyyy): ");
            LocalDate dataNascimento = digitarCampoData();
            usuario.setDataNascimento(dataNascimento);

            System.out.println("Digite sua faixa salarial:");
            ClasseSocial classeSocial = digitarCampoClasseSocial();
            usuario.setClasseSocial(classeSocial);

            System.out.println("Digite o Gênero:");
            Genero genero = digitarCampoGenero();
            usuario.setGeneroUsuario(genero);

            System.out.println("Selecione o Tipo de Usuário:");
            TipoUsuario tipo = digitarCampoTipoDeUsuario();
            usuario.setTipoUsuario(tipo);

        } catch (MaxAttemptsExceededException e) {
            System.out.println("Tipo de entrada inválida: " + e.getMessage());
        }

        return usuario;
    }

    @Override
    public void excluirUsuario(int idUsuario, HashMap<Integer, Usuario> usuarios) {
        if (usuarios.containsKey(idUsuario)) {
            usuarios.remove(idUsuario);
        } else {
            System.out.println("Usuário não encontrado");
        }
    }

    @Override
    public void visualizarUsuario(Usuario usuario) {
        System.out.println("ID do Usuário: " + usuario.getIdUsuario());
        System.out.println("Nome do Usuário: " + usuario.getNomeUsuario());
        System.out.println("Número do Celular: " + usuario.getNumeroCelular());
        System.out.println("Senha do Usuário: " + usuario.getSenhaUsuario());
        System.out.println("Etnia do Usuário: " + usuario.getEtniaUsuario());
        System.out.println("Data de Nascimento: " + usuario.getDataNascimento());
        System.out.println("Classe Social: " + usuario.getClasseSocial());
        System.out.println("Gênero: " + usuario.getGeneroUsuario());
        System.out.println("Tipo do Usuário: " + usuario.getTipoUsuario());
    }
}