package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
    Optional<Instituicao> findByEmailInstituicao(String emailInstituicao);

    Optional<Instituicao> findByIdInstituicao(Integer idInstituicao);

    @Query(value = """
            SELECT i FROM INSTITUICAO i
            WHERE i.emailInstituicao = ?1
            """)
    Optional<Instituicao> getInstituicaoByEmail(String email);

    @Query(value = """
            SELECT i FROM INSTITUICAO i
            WHERE i.celularInstituicao = ?1
            """)
    Optional<Instituicao> getInstituicaoByCelular(String celular);
}
