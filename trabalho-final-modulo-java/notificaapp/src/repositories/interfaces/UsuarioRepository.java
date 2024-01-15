package repositories.interfaces;

import exceptions.DataBaseException;
import models.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

public interface UsuarioRepository <CHAVE, OBJETO> {

    Integer getProximoIdDoUsuario(Connection connection) throws SQLException;

    OBJETO adicionarUsuario(OBJETO object) throws DataBaseException;

    boolean editarUsuario(CHAVE id, OBJETO objeto) throws DataBaseException;

    boolean removerUsuario(CHAVE id) throws DataBaseException;

    boolean editarUsuario(Integer id, Usuario usuario) throws DataBaseException;

    Usuario listarUsuario(int idUsuario) throws DataBaseException;

    Usuario fazerLogin(String nomeUsuario, String senha) throws DataBaseException;

    Usuario getUsuarioPorId(CHAVE idUsuario) throws DataBaseException;
}
