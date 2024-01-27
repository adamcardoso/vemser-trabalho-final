package br.com.dbc.vemser.notifica.service.IService;

import br.com.dbc.vemser.notifica.dto.endereco.CreateEnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.UpdateEnderecoDTO;

import java.util.List;
import java.util.Optional;

public interface IEnderecoService {
    Optional<EnderecoDTO> obterEnderecoById(Integer id) throws Exception;
    Optional<List<EnderecoDTO>> obterEnderecosByIdUsuario(Integer id) throws Exception;
    Optional<EnderecoDTO> adicionarEndereco(CreateEnderecoDTO enderecoDto) throws Exception;
    Optional<EnderecoDTO> atualizarEndereco(Integer id, UpdateEnderecoDTO enderecoDto) throws Exception;
    Optional<Boolean> removerEndereco(Integer id) throws Exception;
}
