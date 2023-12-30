package interfaces;

import entities.Usuario;

public interface IUsuario {
    void CadastrarUsuario(Usuario usuario);
    void EditarUsuario(int idUsuario, Usuario novoUsuario);
    void ExcluirUsuario(int idUsuario);
}
