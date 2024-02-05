package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeedRepository extends JpaRepository<Denuncia, Integer> {
}
