package views.interfaces;

import models.Usuario;

import java.util.HashMap;

public interface IUsuarioCadastro {

    Usuario cadastrarUsuario();

    Usuario editarUsuario(int idUsuario ,Usuario usuario);

    void excluirUsuario(int idUsuario, HashMap<Integer, Usuario> usuario);

    void visualizarUsuario(Usuario usuario);
}
