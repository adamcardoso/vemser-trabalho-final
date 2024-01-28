package br.com.dbc.vemser.notifica.repository.irepository;

import br.com.dbc.vemser.notifica.entity.Endereco;

import java.util.List;
import java.util.Optional;

public interface IEnderecoRepository {
    List<Endereco> listarTodosEnderecos() throws Exception;
    Endereco obterEnderecoById(Integer id) throws Exception;
    List<Endereco> obterEnderecosByIdUsuario(Integer id) throws Exception;
    Endereco adicionarEndereco(Endereco endereco) throws Exception;
    Endereco atualizarEndereco(Integer id, Endereco endereco) throws Exception;
    void removerEndereco(Integer id) throws Exception;
}
