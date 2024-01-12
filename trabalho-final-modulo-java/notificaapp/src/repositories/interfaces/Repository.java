package repositories.interfaces;

import exceptions.DataBaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository<CHAVE, OBJETO> {

    Integer getProximoId(Connection connection) throws SQLException;

    OBJETO adicionar(OBJETO object) throws DataBaseException;

    boolean remover(CHAVE id) throws DataBaseException;

    boolean editar(CHAVE id, OBJETO objeto) throws DataBaseException;

    List<OBJETO> listarUsuariosNoBanco() throws DataBaseException;
}
