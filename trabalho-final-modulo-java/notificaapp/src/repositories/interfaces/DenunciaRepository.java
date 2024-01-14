package repositories.interfaces;

import exceptions.DataBaseException;
import models.Denuncia;
import models.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DenunciaRepository<CHAVE, OBJETO> {
    List<Denuncia> obterTodos();

    Integer getProximoIdDaDenuncia(Connection connection) throws SQLException;

    OBJETO adicionar(OBJETO object) throws DataBaseException;

    boolean editar(CHAVE id, OBJETO objeto) throws DataBaseException;

    boolean removerDenuncia(CHAVE id) throws DataBaseException;

    Usuario listarUsuarioDaDenuncia(CHAVE idUsuario) throws DataBaseException;

    List<Denuncia> listarDenunciasDoUsuario(CHAVE id);
}