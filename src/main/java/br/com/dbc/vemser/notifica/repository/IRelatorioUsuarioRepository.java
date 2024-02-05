package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRelatorioUsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT new br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO(u.idUsuario, u.nomeUsuario, u.emailUsuario, u.numeroCelular, u.etniaUsuario, u.dataNascimento, u.classeSocial, u.generoUsuario) " +
            "FROM br.com.dbc.vemser.notifica.entity.Usuario u " +
            "LEFT JOIN u.enderecos e " +
            //"LEFT JOIN u.denuncias d " +
            "WHERE UPPER(e.estado) = UPPER(?1)")
    Page<UsuarioDTO> usuariosPorEstado(String estado, Pageable pageable);

}