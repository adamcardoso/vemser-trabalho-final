package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.endereco.CreateEnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.UpdateEnderecoDTO;
import br.com.dbc.vemser.notifica.entity.Endereco;
import br.com.dbc.vemser.notifica.repository.irepository.IEnderecoRepository;
import br.com.dbc.vemser.notifica.service.IService.IEnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EnderecoService implements IEnderecoService {
    private final IEnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;

    public Optional<EnderecoDTO> obterEnderecoById(Integer id) throws Exception{
        try{
            Optional<Endereco> enderecoOpt = enderecoRepository.obterEnderecoById(id);

            if(enderecoOpt.isPresent()){
                Endereco endereco = enderecoOpt.get();
                EnderecoDTO eDto = objectMapper.convertValue(endereco, EnderecoDTO.class);

                return Optional.of(eDto);
            }
            return Optional.empty();
        } catch (Exception e){
            throw new Exception();
        }
    }
    public Optional<List<EnderecoDTO>> obterEnderecosByIdUsuario(Integer id) throws Exception{
        try{
            Optional<List<Endereco>> enderecosOpt = enderecoRepository.obterEnderecosByIdUsuario(id);

            if(enderecosOpt.isPresent()){
                List<Endereco> enderecos = enderecosOpt.get();
                List<EnderecoDTO> enderecosDto = new ArrayList<>();

                for(Endereco e: enderecos)
                    enderecosDto.add(objectMapper.convertValue(e, EnderecoDTO.class));

                return Optional.of(enderecosDto);
            }
            return Optional.empty();
        } catch (Exception e){
            throw new Exception();
        }
    }
    public Optional<EnderecoDTO> adicionarEndereco(CreateEnderecoDTO enderecoDto) throws Exception{
        try {
            Endereco e = objectMapper.convertValue(enderecoDto, Endereco.class);
            return Optional.of(objectMapper.convertValue(enderecoRepository.adicionarEndereco(e), EnderecoDTO.class));
        } catch (Exception e){
            throw new Exception();
        }
    }
    public Optional<EnderecoDTO> atualizarEndereco(Integer id, UpdateEnderecoDTO enderecoDto) throws Exception {
        try {
            Optional<Endereco> eOpt = enderecoRepository.obterEnderecoById(id);

            if(eOpt.isPresent()){
                eOpt = enderecoRepository.atualizarEndereco(id, objectMapper.convertValue(enderecoDto, Endereco.class));

                if(eOpt.isPresent()){
                    Endereco e = objectMapper.convertValue(enderecoDto, Endereco.class);
                    return Optional.of(objectMapper.convertValue(enderecoRepository.atualizarEndereco(id, e), EnderecoDTO.class));
                }
            }

            return Optional.empty();
        } catch (Exception e){
            throw new Exception();
        }
    }
    public Optional<Boolean> removerEndereco(Integer id) throws Exception {
        try {
            return enderecoRepository.removerEndereco(id);
        } catch (Exception e){
            throw new Exception();
        }
    }
}
