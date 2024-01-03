package views;

import entities.Denuncia;
import entities.Localizacao;
import entities.Usuario;
import entities.enums.Categoria;
import entities.enums.Situacao;
import entities.enums.TipoUsuario;
import interfaces.IUsuarioCadastro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
public class CadastroUsuario implements IUsuarioCadastro{
    Scanner scanner = new Scanner(System.in);
    public Usuario cadastrarUsuario() {

        Usuario usuario = new Usuario();
        int idUsuario = (int) (Math.random() * 1000);
        usuario.setIdUsuario(idUsuario);

        System.out.println("Digite o nome do usuário:");
        String nomeUsuario = scanner.nextLine();
        usuario.setNomeUsuario(nomeUsuario);

        System.out.println("Digite o número do celular:");
        String numeroCelular = scanner.nextLine();
        usuario.setNumeroCelular(numeroCelular);

        System.out.println("Digite a senha do usuário:");
        String senhaUsuario = scanner.nextLine();
        usuario.setSenhaUsuario(senhaUsuario);

        System.out.println("Digite a Etnia:");
        String etnia = scanner.nextLine();
        usuario.setEtniaUsuario(etnia);

        System.out.println("Selecione o TipoUsuario:");
        for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
            System.out.println(tipoUsuario.ordinal() + " - " + tipoUsuario);
        }
        int opcaoTipo = scanner.nextInt();
        TipoUsuario tipo = TipoUsuario.values()[opcaoTipo];
        usuario.setTipoUsuario(tipo);

        return usuario;
    }

    public Usuario editarUsuario(int idUsuario) {
        //ADICIONAR LÓGICA
        return null;
    }

    public void excluirUsuario(Usuario usuarioLogado) {
        //ADICIONAR LÓGICA
    }

    public void visualizarUsuario(Usuario usuario) {
        System.out.println("ID do Usuário: " + usuario.getIdUsuario());
        System.out.println("Nome do Usuário: " + usuario.getNomeUsuario());
        System.out.println("Número do Celular: " + usuario.getNumeroCelular());
        System.out.println("Senha do Usuário: " + usuario.getSenhaUsuario());
        System.out.println("Etnia do Usuário: " + usuario.getEtniaUsuario());
        System.out.println("Tipo do Usuário: " + usuario.getTipoUsuario());
    }

}
