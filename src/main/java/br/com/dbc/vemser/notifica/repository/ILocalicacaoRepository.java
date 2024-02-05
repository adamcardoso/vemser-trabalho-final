package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocalicacaoRepository extends JpaRepository<Localizacao, Integer> {
}
