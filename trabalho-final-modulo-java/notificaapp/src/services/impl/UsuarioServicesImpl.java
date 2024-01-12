package services.impl;

import exceptions.DataBaseException;
import models.Usuario;
import repositories.impl.UsuarioRepositoryImpl;
import services.interfaces.UsuarioService;

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

    public boolean fazerLogin(String nomeUsuario, String senha){
        try {
            Usuario usuario = usuarioRepository.fazerLogin(nomeUsuario, senha);
            if (usuario != null) {
                System.out.println("Login bem-sucedido!");
                return true;
            } else {
                System.out.println("Nome de usuário ou senha incorretos. Tente novamente.");
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        return false;
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
