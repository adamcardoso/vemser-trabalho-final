package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Endereco;
import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer> {
    @Query("SELECT DISTINCT e.usuario FROM ENDERECO e WHERE e.latitude = :latitude AND e.longitude = :longitude")
    List<Usuario> findUsuariosByLocalizacao(@Param("latitude") String latitude, @Param("longitude") String longitude);
}

