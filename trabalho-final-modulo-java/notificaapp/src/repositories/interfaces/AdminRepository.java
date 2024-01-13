package repositories.interfaces;

import exceptions.DataBaseException;
import models.Denuncia;
import models.Usuario;

import java.util.List;

public interface AdminRepository {

    List<Usuario> listarTodosUsuarios(Usuario usuarioLogado) throws DataBaseException;

    List<Denuncia> listarTodasDenuncias(Usuario usuarioLogado) throws DataBaseException;
}
