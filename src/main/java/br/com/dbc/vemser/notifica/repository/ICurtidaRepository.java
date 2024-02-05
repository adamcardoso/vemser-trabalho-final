package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Curtida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICurtidaRepository extends JpaRepository<Curtida, Integer> {
    @Query(value = "SELECT * FROM CURTIDA cu WHERE cu.ID_USUARIO=?1 AND cu.ID_COMENTARIO=?2", nativeQuery = true)
    Optional<Curtida> findByIdUsuarioAndIdComentario(Integer idUsuario, Integer idComentario);

    @Query(value = "SELECT * FROM CURTIDA cu WHERE cu.ID_USUARIO=?1 AND cu.ID_DENUNCIA=?2", nativeQuery = true)
    Optional<Curtida> findByIdUsuarioAndDenuncia(Integer idUsuario, Integer idDenuncia);

    @Query(value = "SELECT COUNT(*) FROM curtida cu WHERE cu.id_usuario = ?1 AND cu.id_denuncia IS NOT null ", nativeQuery = true)
    Integer curtidasDenuncia(Integer id);

    @Query(value = "SELECT COUNT(*) FROM curtida cu WHERE cu.id_usuario = ?1 AND cu.id_comentario IS NOT null ", nativeQuery = true)
    Integer curtidasComentario(Integer id);
}
