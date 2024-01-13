package services.interfaces;

import models.Usuario;

public interface UsuarioService {
    void listarUsuario(int idUsuario);

    Usuario fazerLogin(String nomeUsuario, String senha);

    void remover(Integer id);
    
    Usuario adicionar(Usuario usuario);
}
