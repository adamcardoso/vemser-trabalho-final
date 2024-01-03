package interfaces;

import entities.Usuario;

import java.util.ArrayList;

public interface IUsuarioCadastro {

    Usuario cadastrarUsuario();

    Usuario editarUsuario(int idUsuario);

    int excluirUsuario(ArrayList<Usuario> usuario);

    void visualizarUsuario();
}
