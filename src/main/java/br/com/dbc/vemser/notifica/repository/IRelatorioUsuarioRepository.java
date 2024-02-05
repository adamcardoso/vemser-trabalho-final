package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRelatorioUsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = """
            SELECT * FROM USUARIO u
            LEFT JOIN ENDERECO e
            ON u.ID_USUARIO = e.ID_USUARIO
            WHERE UPPER(e.ESTADO) = UPPER(?1)
            """, nativeQuery = true)
    Page<Usuario> usuariosPorEstado(String estado, Pageable pageable);
}
