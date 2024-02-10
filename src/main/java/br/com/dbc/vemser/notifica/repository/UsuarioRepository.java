package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.config.ConexaoBancoDeDados;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.*;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = """
            SELECT u FROM USUARIO u
            WHERE (u.numeroCelular = ?1 OR u.emailUsuario = ?2) 
            AND u.usuarioAtivo = 0
            """)
    Usuario usuarioInativoCadastrado(String numeroCelular, String emailUsuario);

}