package services.interfaces;

import models.Denuncia;
import models.Usuario;

import java.util.Optional;

public interface UsuarioService {
    void listarUsuario(int idUsuario);

    Usuario fazerLogin(String nomeUsuario, String senha);

    void remover(Integer id);

    Optional<Usuario> adicionar(Usuario usuario);

}
