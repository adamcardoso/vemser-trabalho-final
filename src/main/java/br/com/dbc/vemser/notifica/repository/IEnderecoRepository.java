package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEnderecoRepository extends JpaRepository<Endereco, Integer> {
    @Query(value = "SELECT * FROM ENDERECO e WHERE e.ID_USUARIO = ?1", nativeQuery = true)
    List<Endereco> obterEnderecosByIdUsuario(Integer id) throws Exception;
}
