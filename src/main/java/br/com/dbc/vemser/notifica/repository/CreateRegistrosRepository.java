package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.CreateRegistros;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreateRegistrosRepository extends MongoRepository<CreateRegistros, String> {

}
