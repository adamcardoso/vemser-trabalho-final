package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT u FROM USUARIO u WHERE u.tipoUsuario = 0 AND u.usuarioAtivo = 1")
    List<Usuario> usuariosAdmin();

    @Query(value = """
            SELECT u FROM USUARIO u
            WHERE (u.numeroCelular = ?1 OR u.emailUsuario = ?2) 
            AND u.usuarioAtivo = 0
            """)
    Usuario usuarioInativoCadastrado(String numeroCelular, String emailUsuario);

    @Query(value = """
            SELECT u FROM USUARIO u
            WHERE u.usuarioAtivo = 1 AND u.idUsuario = ?1
            """)
    Usuario getUsuarioAtivo(Integer idUsuario);


    @Query(value = """
        SELECT u FROM USUARIO u
        WHERE u.tipoUsuario = 1
        ORDER BY u.usuarioAtivo desc
        """)
    List<Usuario>listAll();

}