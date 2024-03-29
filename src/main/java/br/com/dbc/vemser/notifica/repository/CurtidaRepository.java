package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Curtida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CurtidaRepository extends JpaRepository<Curtida, Integer> {
    @Query(value = "SELECT * FROM VS_13_EQUIPE_7.CURTIDA cu WHERE cu.ID_USUARIO=?1 AND cu.ID_COMENTARIO=?2", nativeQuery = true)
    Optional<Curtida> findByIdUsuarioAndIdComentario(Integer idUsuario, Integer idComentario);

    @Query(value = "SELECT * FROM VS_13_EQUIPE_7.CURTIDA cu WHERE cu.ID_USUARIO=?1 AND cu.ID_DENUNCIA=?2", nativeQuery = true)
    Optional<Curtida> findByIdUsuarioAndDenuncia(Integer idUsuario, Integer idDenuncia);

    @Query(value = "SELECT COUNT(*) FROM VS_13_EQUIPE_7.curtida cu WHERE cu.id_usuario = ?1 AND cu.id_denuncia IS NOT null ", nativeQuery = true)
    Integer curtidasDenuncia(Integer id);

    @Query(value = "SELECT COUNT(*) FROM VS_13_EQUIPE_7.curtida cu WHERE cu.id_usuario = ?1 AND cu.id_comentario IS NOT null ", nativeQuery = true)
    Integer curtidasComentario(Integer id);

    @Query(value = """
            SELECT COUNT(c) FROM CURTIDA c
            WHERE c.idDenuncia = ?1
            """)
    Integer numereCurtidasByDenuncia(Integer idDenuncia);

    @Query(value = """
            SELECT COUNT(c) FROM CURTIDA c
            WHERE c.idComentario = ?1
            """)
    Integer numereCurtidasByComentario(Integer idComentario);
}
