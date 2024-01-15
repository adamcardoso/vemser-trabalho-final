package repositories.interfaces;

import exceptions.DataBaseException;
import models.Denuncia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DenunciaRepository<CHAVE, OBJETO> {
    List<Denuncia> obterTodosFeed();

    Integer getProximoIdDaDenuncia(Connection connection) throws SQLException;

    OBJETO adicionarDenuncia(OBJETO object) throws DataBaseException;

    boolean editarDenuncia(CHAVE id, OBJETO objeto) throws DataBaseException;

    boolean removerDenuncia(CHAVE id1, CHAVE id2) throws DataBaseException;

    List<Denuncia> listarDenunciasDoUsuario(CHAVE id);
}