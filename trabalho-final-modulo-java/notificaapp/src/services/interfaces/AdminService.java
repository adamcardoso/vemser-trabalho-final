package services.interfaces;

import models.Usuario;

public interface AdminService {
    void listarUsuarios(Usuario usuarioLogado);

    void listarDenuncias(Usuario usuarioLogado);

    boolean editarDadosDoAdmin(Integer id, Usuario usuario);

    boolean excluirDenuncia(int idDenuncia);
}
