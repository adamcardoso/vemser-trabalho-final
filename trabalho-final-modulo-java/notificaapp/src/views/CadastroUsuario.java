package views;

import entities.Denuncia;
import entities.Localizacao;
import entities.Usuario;
import entities.enums.Categoria;
import entities.enums.Situacao;
import entities.enums.TipoUsuario;
import interfaces.IUsuarioCadastro;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
public class CadastroUsuario implements IUsuarioCadastro{
    Scanner scanner = new Scanner(System.in);
    public Usuario cadastrarUsuario() {
        //CRIADO LOGICA DE CADASTRAR - REVISÃO NECESSÁRIA
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
        scanner.nextLine();
        TipoUsuario tipo = TipoUsuario.values()[opcaoTipo];
        usuario.setTipoUsuario(tipo);

        System.out.println("Digite o gênero do usuário:");
        String generoUsuario = scanner.nextLine();
        usuario.setGeneroUsuario(generoUsuario);

        System.out.println("Digite a data de nascimento (dd/mm/aaaa):");
        String dataNascimentoInput = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoInput, formatter);

        Date dataNascimentoDate = Date.from(dataNascimento.atStartOfDay(ZoneId.systemDefault()).toInstant());
        usuario.setDataNascimento(dataNascimentoDate);

        System.out.println("Digite a classe social:");
        String classeSocial = scanner.nextLine();
        usuario.setClasseSocial(classeSocial);

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
        System.out.println("Genero: " + usuario.getGeneroUsuario());
        System.out.println("Data Nascimento: " + usuario.getDataNascimento());
        System.out.println("Classe Social: " + usuario.getClasseSocial());
    }

}
