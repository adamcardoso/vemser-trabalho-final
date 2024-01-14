package services.interfaces;

import models.Usuario;

import java.util.Optional;

public interface UsuarioService {
    void listarUsuario(int idUsuario);

    Usuario fazerLogin(String nomeUsuario, String senha);

    void remover(Integer id);
    
    Usuario adicionar(Usuario usuario);

    Optional<Usuario> getUsuarioPorId(Integer idUsuario);
}
