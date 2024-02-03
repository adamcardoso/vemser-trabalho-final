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
    @Query(value = "SELECT u FROM USUARIO u WHERE u.tipoUsuario = 0")
    List<Usuario> usuariosAdmin();

}