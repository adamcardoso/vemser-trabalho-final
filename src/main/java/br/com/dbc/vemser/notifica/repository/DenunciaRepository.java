package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.config.ConexaoBancoDeDados;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.enums.Categoria;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;
import feign.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {

    List<Denuncia> findAllByUsuario_IdUsuario(Integer idUsuario);

    @Query(value = """
        SELECT d FROM DENUNCIA d
        WHERE d.statusDenuncia != 4 AND d.idDenuncia = ?1
        """)
    Denuncia getDenunciaAtiva(Integer idDenuncia);

    @Query(value = """
        SELECT d FROM DENUNCIA d
        WHERE d.statusDenuncia != 4
        """)
    List<Denuncia> getDenuncias();

    @Query(value = """
        SELECT d FROM DENUNCIA d
        WHERE d.statusDenuncia != 4 AND d.idUsuario = ?1
        """)
    List<Denuncia> getDenunciaAtivaByIdUsuario(Integer idUsuario);

    Page<Denuncia> findByStatusDenunciaNot(StatusDenuncia status, Pageable pageable);

    Optional<Denuncia> findByIdDenunciaAndStatusDenunciaNot(Integer idDenuncia, StatusDenuncia status);
}
