package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoUpdateDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Endereco;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.TipoEndereco;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.IEnderecoRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {
    @Mock
    private IEnderecoRepository enderecoRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ObjectMapper objectMapper;
    @Spy
    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private RestTemplate restTemplate; // Mock do RestTemplate

    @Test
    @DisplayName("Deveria retornar um endereço pelo id")
    public void obterEnderecoByIdComSucesso() throws Exception {
        // ARRANGE
        Integer idEndereco = 1;
        Integer idUsuario = 1;
        TipoEndereco tipoEndereco = TipoEndereco.RESIDENCIAL;
        String logradouro = "Rua A";
        Integer numero = 1;
        String complemento = "";
        String cep = "00011122";
        String bairro = "Bairro B";
        String cidade = "Cidade";
        String estado = "WA";
        String pais = "País das maravilhas";

        Endereco endereco = new Endereco(idEndereco, idUsuario, tipoEndereco, logradouro, numero, complemento,
                cep, bairro, cidade, estado, pais, new Usuario());

        EnderecoDTO enderecoDTO = new EnderecoDTO(idEndereco, tipoEndereco, logradouro, numero, complemento, cep, cidade,
                estado, pais, idUsuario);


        //ACT
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(endereco));
        when(objectMapper.convertValue(endereco, EnderecoDTO.class)).thenReturn(enderecoDTO);

        EnderecoDTO eDTO = enderecoService.obterEnderecoById(idEndereco);

        assertNotNull(eDTO);
    }


    @Test
    @DisplayName("Deveria lançar uma exception")
    void obterEnderecoByIdComException() throws Exception {
        // ARRANGE
        Integer idEndereco = 1;

        // ACT
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.empty());

        // ASSERT
        assertThrows(RegraDeNegocioException.class, () -> enderecoService.obterEnderecoById(idEndereco),
                "Lançando exception");
        verify(enderecoService).obterEnderecoById(idEndereco);

    }

    @Test
    @DisplayName("Deveria obter endereços pelo id do usuário")
    void obterEnderecosByIdUsuarioComSucesso() throws Exception {
        // ARRANGE
        Integer idEndereco = 1;
        Integer idUsuario = 1;
        TipoEndereco tipoEndereco = TipoEndereco.RESIDENCIAL;
        String logradouro = "Rua A";
        Integer numero = 1;
        String complemento = "";
        String cep = "00011122";
        String bairro = "Bairro B";
        String cidade = "Cidade";
        String estado = "WA";
        String pais = "País das maravilhas";

        Endereco endereco = new Endereco(idEndereco, idUsuario, tipoEndereco, logradouro, numero, complemento,
                cep, bairro, cidade, estado, pais, new Usuario());

        EnderecoDTO enderecoDTO = new EnderecoDTO(idEndereco, tipoEndereco, logradouro, numero, complemento, cep, cidade,
                estado, pais, idUsuario);

        List<Endereco> enderecos = Arrays.asList(endereco, endereco);
        List<EnderecoDTO> enderecosDTO = Arrays.asList(enderecoDTO, enderecoDTO);

        // ACT
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(endereco));
        when(enderecoRepository.obterEnderecosByIdUsuario(anyInt())).thenReturn(enderecos);
        when(objectMapper.convertValue(endereco, EnderecoDTO.class)).thenReturn(enderecoDTO);

        List<EnderecoDTO> lista = enderecoService.obterEnderecosByIdUsuario(idUsuario);

        // ASSERT
        assertNotNull(lista);
        assertEquals(enderecosDTO, lista);
    }

    @Test
    @DisplayName("Deveria lançar uma exception")
    void obterEnderecosByIdUsuarioFail() throws Exception {
        // ARRANGE
        Integer idEndereco = 123;

        // ACT
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.empty());

        // ASSERT
        assertThrows(RegraDeNegocioException.class, () -> enderecoService.obterEnderecoById(idEndereco),
                "Lançando exception");
        verify(enderecoService).obterEnderecoById(idEndereco);
    }

    @Test
    @DisplayName("Deveria criar um endereço")
    void adicionarEnderecoComSucesso() throws Exception {
        // ARRANGE
        Integer idEndereco = 1;
        Integer idUsuario = 1;
        TipoEndereco tipoEndereco = TipoEndereco.RESIDENCIAL;
        String logradouro = "Rua A";
        Integer numero = 1;
        String complemento = "";
        String cep = "21031720";
        String bairro = "Bairro B";
        String cidade = "Cidade";
        String estado = "WA";
        String pais = "País das maravilhas";

        Endereco endereco = new Endereco(idEndereco, idUsuario, tipoEndereco, logradouro, numero, complemento,
                cep, bairro, cidade, estado, pais, new Usuario());

        EnderecoCreateDTO enderecoCreateDTO = new EnderecoCreateDTO(tipoEndereco, logradouro, numero, complemento, bairro, cep, cidade,
                estado, pais, "15.000", "15.000");

        EnderecoDTO enderecoDTORetornado = new EnderecoDTO(idEndereco, tipoEndereco, logradouro, numero, complemento, cep, cidade,
                estado, pais, idUsuario);

        // ACT
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(new Usuario()));
        when(objectMapper.convertValue(enderecoCreateDTO, Endereco.class)).thenReturn(endereco);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(objectMapper.convertValue(endereco, EnderecoDTO.class)).thenReturn(enderecoDTORetornado);

        EnderecoDTO eDTO = enderecoService.adicionarEndereco(idUsuario, enderecoCreateDTO);

        // ASSERT
        assertNotNull(eDTO);
    }

    @Test
    @DisplayName("Deveria lançar uma exception")
    void adicionarEnderecoComFalha1() throws Exception {
        // ARRANGE
        Integer idEndereco = 1;
        Integer idUsuario = 1;
        TipoEndereco tipoEndereco = TipoEndereco.RESIDENCIAL;
        String logradouro = "Rua A";
        Integer numero = 1;
        String complemento = "";
        String cep = "00011122";
        String bairro = "Bairro B";
        String cidade = "Cidade";
        String estado = "WA";
        String pais = "País das maravilhas";

        Endereco endereco = new Endereco(idEndereco, idUsuario, tipoEndereco, logradouro, numero, complemento,
                cep, bairro, cidade, estado, pais, new Usuario());

        EnderecoCreateDTO enderecoCreateDTO = new EnderecoCreateDTO(tipoEndereco, logradouro, numero, complemento, bairro, cep, cidade,
                estado, pais, "15.000", "15.000");

        EnderecoDTO enderecoDTORetornado = new EnderecoDTO(idEndereco, tipoEndereco, logradouro, numero, complemento, cep, cidade,
                estado, pais, idUsuario);

        // ACT
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(new Usuario()));
        when(objectMapper.convertValue(enderecoCreateDTO, Endereco.class)).thenReturn(endereco);

        // ASSERT
//        assertNotNull(eDTO);

        assertThrows(RegraDeNegocioException.class, () -> enderecoService.adicionarEndereco(idUsuario, enderecoCreateDTO));
    }

    @Test
    @DisplayName("Deveria lançar uma exception de usuário não encontrado")
    void adicionarEnderecoComFalha() throws Exception {
        // ARRANGE
        Integer idEndereco = 1;

        // ACT
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.empty());

        // ASSERT
        assertThrows(RegraDeNegocioException.class, () -> enderecoService.obterEnderecoById(idEndereco),
                "Usuário não encontrado!");
        verify(enderecoService).obterEnderecoById(idEndereco);
    }

    @Test
    @DisplayName("Deveria atualizar um endereço com sucesso")
    void atualizarEnderecoComSucesso() throws Exception {
        // ARRANGE
        Integer idEndereco = 1;
        Integer idUsuario = 1;
        TipoEndereco tipoEndereco = TipoEndereco.RESIDENCIAL;
        String logradouro = "Rua A";
        Integer numero = 1;
        String complemento = "";
        String cep = "21031720";
        String bairro = "Bairro B";
        String cidade = "Cidade";
        String estado = "WA";
        String pais = "País das maravilhas";

        Endereco endereco = new Endereco(idEndereco, idUsuario, tipoEndereco, logradouro, numero, complemento,
                cep, bairro, cidade, estado, pais, new Usuario());

        EnderecoUpdateDTO enderecoUpdateDTO = new EnderecoUpdateDTO(tipoEndereco, numero, complemento, cep, pais, idUsuario);

        EnderecoDTO enderecoDTORetornado = new EnderecoDTO(idEndereco, tipoEndereco, logradouro, numero, complemento, cep, cidade,
                estado, pais, idUsuario);

        // ACT
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(endereco));
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(new Usuario()));
        when(objectMapper.convertValue(enderecoUpdateDTO, Endereco.class)).thenReturn(endereco);
        when(enderecoRepository.save(any())).thenReturn(endereco);
        when(objectMapper.convertValue(endereco, EnderecoDTO.class)).thenReturn(enderecoDTORetornado);

        EnderecoDTO eDTO = enderecoService.atualizarEndereco( idEndereco,idUsuario, enderecoUpdateDTO);

        // ASSERT
        assertNotNull(eDTO);
    }

    @Test
    @DisplayName("Deveria lançar uma exception de usuário não encontrado")
    void atualizarEnderecoComFalha() throws Exception {
        // ARRANGE
        Integer idEndereco = 1;

        // ACT
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.empty());

        // ASSERT
        assertThrows(RegraDeNegocioException.class, () -> enderecoService.obterEnderecoById(idEndereco),
                "Usuário não encontrado!");
        verify(enderecoService).obterEnderecoById(idEndereco);
    }

    @Test
    @DisplayName("Deveria remover um endereço pelo id")
    void removerEnderecoComSucesso() throws Exception {
        // ARRANGE
        Integer idEndereco = 1;

        // ACT
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(new Endereco()));
        enderecoService.removerEndereco(idEndereco);

        // ASSERT
        verify(enderecoService, times(1)).removerEndereco(idEndereco);

        verify(enderecoRepository, times(1)).deleteById(idEndereco);
    }
}


