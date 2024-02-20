package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.DenunciaRegistros;
import br.com.dbc.vemser.notifica.entity.enums.TipoRegistro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DenunciaRegistroRepository extends MongoRepository<DenunciaRegistros, String> {
    @Query("{'data_hora': {$gte: ?0}, 'tipo_registro': ?1}")
    List<DenunciaRegistros> findAllAfterDateTimeAndTipoRegistroInicio(LocalDate dataHora, TipoRegistro tipoRegistro);


    @Query("{'data_hora': {$lt: ?0}, 'tipo_registro': ?1}")
    List<DenunciaRegistros> findAllBeforeDateAndTipoRegistroInicio(LocalDate dataHora, TipoRegistro tipoRegistro);


    @Query("{'data_hora': {$gte: ?0}, 'tipo_registro': ?1}")
    List<DenunciaRegistros> findAllAfterDateTimeAndTipoRegistroFechamento(LocalDate dataHora, TipoRegistro tipoRegistro);


    @Query("{'data_hora': {$lt: ?0}, 'tipo_registro': ?1}")
    List<DenunciaRegistros> findAllBeforeDateAndTipoRegistroFechamento(LocalDate dataHora, TipoRegistro tipoRegistro);
}
