package interfaces;

import entities.Usuario;

public interface IUsuario {
    void cadastrarUsuario(Usuario usuario);
    void editarUsuario(int idUsuario, Usuario novoUsuario);
    void excluirUsuario(int idUsuario);
}
