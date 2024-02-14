package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Avisos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisosRepository extends JpaRepository<Avisos, Integer> {

}
