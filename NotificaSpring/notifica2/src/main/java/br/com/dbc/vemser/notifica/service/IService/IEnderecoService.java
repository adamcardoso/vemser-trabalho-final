package br.com.dbc.vemser.notifica.service.IService;

import br.com.dbc.vemser.notifica.dto.endereco.CreateEnderecoDto;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDto;
import br.com.dbc.vemser.notifica.dto.endereco.UpdateEnderecoDto;

import java.util.List;
import java.util.Optional;

public interface IEnderecoService {
    Optional<EnderecoDto> obterEnderecoById(Integer id) throws Exception;
    Optional<List<EnderecoDto>> obterEnderecosByIdUsuario(Integer id) throws Exception;
    Optional<EnderecoDto> adicionarEndereco(CreateEnderecoDto enderecoDto) throws Exception;
    Optional<EnderecoDto> atualizarEndereco(Integer id, UpdateEnderecoDto enderecoDto) throws Exception;
    Optional<Boolean> removerEndereco(Integer id) throws Exception;
}
