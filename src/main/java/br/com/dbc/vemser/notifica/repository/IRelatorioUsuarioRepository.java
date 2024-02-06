package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRelatorioUsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = """
            SELECT * FROM VS_13_EQUIPE_7.USUARIO u
            LEFT JOIN VS_13_EQUIPE_7.ENDERECO e
            ON u.id_usuario = e.id_usuario
            WHERE UPPER(e.estado) = UPPER(?1)
            """, nativeQuery = true)
    Page<Usuario> usuariosPorEstado(String estado, Pageable pageable);

    @Query(value = """
            SELECT * FROM VS_13_EQUIPE_7.USUARIO u
            LEFT JOIN VS_13_EQUIPE_7.ENDERECO e
            ON u.id_usuario = e.id_usuario
            WHERE UPPER(e.cidade) LIKE UPPER(?1)
            """, nativeQuery = true)
    Page<Usuario> usuariosPorCidade(String cidade, Pageable pageable);

}