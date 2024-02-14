package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    @Query(value = "SELECT * FROM VS_13_EQUIPE_7.COMENTARIO c WHERE c.ID_DENUNCIA=?1", nativeQuery = true)
    List<Comentario> listarComentariosByIdDenuncia(Integer id) throws Exception;

}
