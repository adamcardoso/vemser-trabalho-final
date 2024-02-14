package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
    Optional<Instituicao> findByEmailInstituicao(String emailInstituicao);

    Optional<Instituicao> findByIdInstituicao(Integer idInstituicao);
}
