package services.impl;

import config.ConexaoBancoDeDados;
import exceptions.DataBaseException;
import models.Usuario;
import models.enums.Etnia;
import repositories.impl.UsuarioRepositoryImpl;
import services.interfaces.UsuarioService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UsuarioServicesImpl implements UsuarioService {

    private final UsuarioRepositoryImpl usuarioRepository;

    public UsuarioServicesImpl() {
        this.usuarioRepository = new UsuarioRepositoryImpl();
    }

    @Override
    public void listarUsuarios() {
        try {
            List<Usuario> listarUsuarios = usuarioRepository.listarUsuariosNoBanco();
            listarUsuarios.forEach(this::imprimirUsuario);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    private void imprimirUsuario(Usuario usuario) {
        System.out.println("ID: " + usuario.getIdUsuario());
        System.out.println("Nome: " + usuario.getNomeUsuario());
        System.out.println("Número de Celular: " + usuario.getNumeroCelular());
        System.out.println("Senha: " + usuario.getSenhaUsuario());
        System.out.println("Etnia: " + usuario.getEtniaUsuario());
        System.out.println("Data de Nascimento: " + usuario.getDataNascimento());
        System.out.println("------------------------");
    }

}
