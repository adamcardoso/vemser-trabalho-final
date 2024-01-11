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
            listarUsuarios.forEach(System.out::println);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }
}
