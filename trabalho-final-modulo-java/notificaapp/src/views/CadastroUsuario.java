package views;

import entities.Usuario;
import entities.enums.TipoUsuario;
import interfaces.IUsuarioCadastro;

import java.util.ArrayList;
import java.util.Date;

public class CadastroUsuario implements IUsuarioCadastro {

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        return null;
    }
    @Override
    public Usuario editarUsuario(int idUsuario) {
        return null;
    }

    @Override
    public int excluirUsuario(ArrayList<Usuario> usuario) {
        return 0;
    }

    @Override
    public void visualizarUsuario() {

    }
}
