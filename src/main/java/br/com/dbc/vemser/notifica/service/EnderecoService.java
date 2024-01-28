package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.endereco.CreateEnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.UpdateEnderecoDTO;
import br.com.dbc.vemser.notifica.entity.Endereco;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.EnderecoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EnderecoService{
    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;

    public EnderecoDTO obterEnderecoById(Integer id) throws Exception{
        return objectMapper.convertValue(getEndereco(id), EnderecoDTO.class);
    }
    public List<EnderecoDTO> obterEnderecosByIdUsuario(Integer id) throws Exception{
        List<Endereco> enderecosIdUsuario = enderecoRepository.ListarTodosEnderecos().stream()
                .filter(endereco -> endereco.getIdPessoa().equals(id))
                .collect(Collectors.toList());

        enderecosIdUsuario.stream().findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));

        return objectMapper.convertValue(enderecosIdUsuario, new TypeReference<List<EnderecoDTO>>(){});
    }
    public EnderecoDTO adicionarEndereco(CreateEnderecoDTO enderecoDto) throws Exception{
        Endereco enderecoAdicionado = objectMapper.convertValue(enderecoDto, Endereco.class);
        enderecoAdicionado = enderecoRepository.adicionarEndereco(enderecoAdicionado);
        return objectMapper.convertValue(enderecoAdicionado, EnderecoDTO.class);
    }
    public EnderecoDTO atualizarEndereco(Integer id, UpdateEnderecoDTO enderecoDto) throws Exception {
        Endereco enderecoAtualizado = objectMapper.convertValue(enderecoDto, Endereco.class);
        enderecoAtualizado = enderecoRepository.atualizarEndereco(getEndereco(id).getIdEndereco(), enderecoAtualizado);
        return objectMapper.convertValue(enderecoAtualizado, EnderecoDTO.class);
    }
    public void removerEndereco(Integer id) throws Exception {
        enderecoRepository.removerEndereco(getEndereco(id).getIdEndereco());
    }

    private Endereco getEndereco(Integer id) throws Exception{
        Endereco enderecoGet = enderecoRepository.ListarTodosEnderecos().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));

        return enderecoGet;
    }
}
