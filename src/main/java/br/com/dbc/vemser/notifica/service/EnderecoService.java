package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoUpdateDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Endereco;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.IEnderecoRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EnderecoService{
    private final IEnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public EnderecoDTO obterEnderecoById(Integer id) throws Exception{
        return enderecoRepository.findById(id)
                .map(this::returnEnderecoDto)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
    }
    public List<EnderecoDTO> obterEnderecosByIdUsuario(Integer id) throws Exception{
        enderecoRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));

        return enderecoRepository.obterEnderecosByIdUsuario(id)
                .stream()
                .map(this::returnEnderecoDto)
                .collect(Collectors.toList());
    }
    public EnderecoDTO adicionarEndereco(Integer idUsuario, EnderecoCreateDTO enderecoDto) throws Exception {
        Usuario u = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado!"));
        Endereco e = objectMapper.convertValue(enderecoDto, Endereco.class);
        e.setUsuario(u);

        EnderecoDTO eDto = objectMapper.convertValue(enderecoRepository.save(cepRequest(e)), EnderecoDTO.class);
        eDto.setIdUsuario(u.getIdUsuario());
        return eDto;
    }

    public EnderecoDTO atualizarEndereco(Integer idEndereco, Integer idUsuario, EnderecoUpdateDTO enderecoDto) throws Exception {
        enderecoRepository.findById(idEndereco).orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));

        Usuario u = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado!"));
        Endereco e = objectMapper.convertValue(enderecoDto, Endereco.class);
        e.setIdEndereco(idEndereco);
        e.setUsuario(u);

        EnderecoDTO eDto = objectMapper.convertValue(enderecoRepository.save(cepRequest(e)), EnderecoDTO.class);
        eDto.setIdUsuario(u.getIdUsuario());
        return eDto;
    }
    public void removerEndereco(Integer id) throws Exception {
        enderecoRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
        enderecoRepository.deleteById(id);
    }

    private EnderecoDTO returnEnderecoDto(Endereco e){
        EnderecoDTO enderecoDTO = objectMapper.convertValue(e, EnderecoDTO.class);
        enderecoDTO.setIdUsuario(e.getUsuario().getIdUsuario());
        return enderecoDTO;
    }

    private Endereco cepRequest(Endereco endereco) throws RegraDeNegocioException {
        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = """
            https://viacep.com.br/ws/%s/json/
            """.formatted(endereco.getCep());

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

            String[] jason = Objects.requireNonNull(response.getBody()).replace("[{}\\\"]", "").split(",");

            List<String> el = new ArrayList<>();
            for (String item : jason)
                el.add(item.replace("\\", "").split(": ")[1]);

            return new Endereco(endereco.getIdEndereco(), endereco.getTipoEndereco(), el.get(1).replace("\"", ""),
                    endereco.getNumero(), endereco.getComplemento(), endereco.getCep(), el.get(4).replace("\"", ""),
                    el.get(5).replace("\"", ""), endereco.getPais(), endereco.getUsuario());
        } catch (Exception e) {
            throw new RegraDeNegocioException("CEP Inválido!");
        }
    }

}