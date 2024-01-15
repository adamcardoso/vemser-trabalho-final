package services.interfaces;

import models.Usuario;

import java.util.Optional;

public interface UsuarioService {
    void listarUsuario(int idUsuario);

    Usuario fazerLogin(String nomeUsuario, String senha);

    void removerUsuario(Integer id);

    Optional<Usuario> adicionarUsuario(Usuario usuario);

    Optional<Usuario> getUsuarioPorId(Integer idUsuario);
}
