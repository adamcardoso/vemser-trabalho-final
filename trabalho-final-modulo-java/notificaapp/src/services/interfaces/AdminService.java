package services.interfaces;

import models.Usuario;

import java.util.Optional;

public interface AdminService {
    void listarUsuarios(Usuario usuarioLogado);

    void listarDenuncias(Usuario usuarioLogado);

    Optional<Usuario> adicionarUsuario(Usuario u);
    boolean excluirDenuncia(int idDenuncia);
}
