package br.com.dbc.vemser.notifica.service.IService;

import br.com.dbc.vemser.notifica.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface IEnderecoService {
    Optional<EnderecoDTO> obterEnderecoById(Integer id) throws Exception;
    Optional<List<EnderecoDTO>> obterEnderecosByIdUsuario(Integer id) throws Exception;
    Optional<EnderecoDTO> adicionarEndereco(EnderecoCreateDTO enderecoDto) throws Exception;
    Optional<EnderecoDTO> atualizarEndereco(Integer id, EnderecoUpdateDTO enderecoDto) throws Exception;
    Optional<Boolean> removerEndereco(Integer id) throws Exception;
}
