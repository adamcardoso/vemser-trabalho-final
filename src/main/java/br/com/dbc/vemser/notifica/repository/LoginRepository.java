package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmailUsuarioAndSenhaUsuario(String email, String senha);

    Optional<Usuario> findByEmailUsuario(String email);

    Optional<Usuario> findByIdUsuario(Integer idUsuario);

    Optional<Usuario> findByNumeroCelular(String numeroCelular);
}
