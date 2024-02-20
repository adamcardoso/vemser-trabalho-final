package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.CreateRegistros;
import br.com.dbc.vemser.notifica.entity.DenunciaRegistros;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CreateRegistrosRepository extends MongoRepository<CreateRegistros, String> {
    @Query("{'data_hora': {$gte: ?0}}")
    List<CreateRegistros> findAllAfterDate(LocalDate data);

    @Query("{'data_hora': {$lt: ?0}}")
    List<CreateRegistros> findAllBeforeDate(LocalDate data);

}
