package br.com.dbc.vemser.notifica.repository.irepository;

import br.com.dbc.vemser.notifica.entity.Endereco;

import java.util.List;
import java.util.Optional;

public interface IEnderecoRepository {
    Optional<Endereco> obterEnderecoById(Integer id) throws Exception;
    Optional<List<Endereco>> obterEnderecosByIdUsuario(Integer id) throws Exception;
    Optional<Endereco> adicionarEndereco(Endereco endereco) throws Exception;
    Optional<Endereco> atualizarEndereco(Integer id, Endereco endereco) throws Exception;
    Optional<Boolean> removerEndereco(Integer id) throws Exception;
}
