package services.interfaces;

import models.Usuario;

public interface AdminService {
    void listarUsuarios(Usuario usuarioLogado);

    void listarDenuncias(Usuario usuarioLogado);

    boolean excluirDenuncia(int idDenuncia);
}
