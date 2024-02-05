package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.config.ConexaoBancoDeDados;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.*;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}