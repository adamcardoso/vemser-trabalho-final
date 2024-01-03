package interfaces;

import entities.Usuario;

import java.util.ArrayList;

public interface IUsuarioCadastro {

    Usuario cadastrarUsuario();

    Usuario editarUsuario(int idUsuario);

    void excluirUsuario(Usuario IdUsuarioLogado);

    void visualizarUsuario(Usuario usuario);
}
