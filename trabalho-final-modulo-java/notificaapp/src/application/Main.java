package application;

import exceptions.DataBaseException;
import models.Usuario;
import models.enums.Categoria;
import models.enums.ClasseSocial;
import models.enums.Etnia;
import models.enums.Genero;
import repositories.impl.UsuarioRepositoryImpl;
import views.Home;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws DataBaseException {
        //Home home = new Home();
        //home.exibirLoginMenu();
        Usuario usuario = new Usuario("Teste", "12345678910", "Senha123",
                LocalDate.parse("01-11-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                Etnia.AMARELO, ClasseSocial.E, Genero.FEMININO);

        UsuarioRepositoryImpl usuarioRepository = new UsuarioRepositoryImpl();
        Usuario u = usuarioRepository.adicionarUsuario(usuario);
        System.out.printf("""
                        id: %s
                        nome: %s
                        celula: %s
                        senha: %s
                        data nascimento: %s
                        etnia: %s
                        classe social: %s
                        genero: %s
                        tipo usuario: %s
                        %n""",
                u.getIdUsuario(),
                u.getNomeUsuario(),
                u.getNumeroCelular(),
                u.getSenhaUsuario(),
                u.getDataNascimento(),
                u.getEtniaUsuario(),
                u.getClasseSocial(),
                u.getGeneroUsuario(),
                u.getTipoUsuario());
    }
}