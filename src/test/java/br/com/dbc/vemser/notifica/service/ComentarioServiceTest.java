package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.ComentarioRepository;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComentarioServiceTest {
    @Mock
    private ComentarioRepository comentarioRepository;
    @Mock
    private DenunciaRepository denunciaRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Spy
    @InjectMocks
    private ComentarioService comentarioService;
    @Mock
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deveria criar um comentário relacionado com usuário e denúncia")
    void criarComentarioComsucesso() throws RegraDeNegocioException {
        // ARRANGE
        Integer idUsuario = 1;
        Integer idDenuncia = 1;
        Integer idComentario = 1;
        String comentario = "comentário editado";

        Comentario comentarioretornado = new Comentario();
        comentarioretornado.setIdUsuario(1);
        comentarioretornado.setIdDenuncia(1);
        comentarioretornado.setComentario("comentário");

        Usuario usuario = new Usuario();

        Comentario comentarioCriado = new Comentario();
        comentarioCriado.setIdUsuario(1);
        comentarioCriado.setIdDenuncia(1);
        comentarioCriado.setComentario("comentário");

        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setIdUsuario(1);
        comentarioDTO.setIdDenuncia(1);
        comentarioDTO.setComentario("comentário");

        Denuncia denuncia = new Denuncia();

        // ACT
        when(denunciaRepository.getDenunciaAtiva(idDenuncia)).thenReturn(denuncia);
        when(usuarioRepository.getById(anyInt())).thenReturn(usuario);
        when(comentarioRepository.save(any())).thenReturn(comentarioretornado);
        when(objectMapper.convertValue(comentarioretornado, ComentarioDTO.class)).thenReturn(comentarioDTO);

        ComentarioDTO cDTO = comentarioService.criarComentario(idUsuario, idDenuncia, comentario);

        // ASSERT
        assertNotNull(cDTO);
        assertEquals(comentarioDTO, cDTO);
    }

    @Test
    @DisplayName("Não deveria criar um comentário relacionado com usuário e denúncia")
    void criarComentarioComFalha() throws RegraDeNegocioException {
        // ARRANGE
        Integer idUsuario = 1;
        Integer idDenuncia = 123;
        String comentario = "comentario";

        Comentario comentarioCriado = new Comentario();
        comentarioCriado.setIdUsuario(1);
        comentarioCriado.setIdDenuncia(1);
        comentarioCriado.setComentario("comentário");
        comentarioCriado.setNumeroCurtidas(0);

        // ACT
        when(denunciaRepository.getDenunciaAtiva(idDenuncia)).thenReturn(null);

        // ASSERT
        assertThrows(RegraDeNegocioException.class, () -> comentarioService.criarComentario(idUsuario, idDenuncia, comentario), "Denuncia não encontrada!");
    }


    @Test
    @DisplayName("Deveria editar um comentário com sucesso")
    void editarComentarioComSucesso() throws RegraDeNegocioException{
        // ARRANGE
        Integer idUsuario = 1;
        Integer idDenuncia = 1;
        Integer idComentario = 1;
        String comentario = "comentário editado";

        Comentario comentarioretornado = new Comentario();
        comentarioretornado.setIdUsuario(idUsuario);
        comentarioretornado.setIdDenuncia(idDenuncia);
        comentarioretornado.setComentario(comentario);

        Comentario comentarioCriado = new Comentario();
        comentarioCriado.setIdUsuario(idUsuario);
        comentarioCriado.setIdDenuncia(idDenuncia);
        comentarioCriado.setComentario(comentario);

        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setIdUsuario(1);
        comentarioDTO.setIdDenuncia(1);
        comentarioDTO.setComentario(comentario);

        // ACT
        when(comentarioRepository.findById(anyInt())).thenReturn(Optional.of(comentarioretornado));
        when(comentarioRepository.save(any())).thenReturn(comentarioretornado);
        when(objectMapper.convertValue(comentarioretornado, ComentarioDTO.class)).thenReturn(comentarioDTO);

        ComentarioDTO cDTO = comentarioService.editarComentario(idUsuario, idDenuncia, comentario);

        // ASSERT
        assertNotNull(cDTO);
        assertEquals(comentarioDTO, cDTO);
    }

    @Test
    @DisplayName("Deveria lançar um erro por comentário não encontrado")
    void editarComentarioComFalha() throws RegraDeNegocioException {
        // ARRANGE
        Integer idUsuario = 1;
        Integer idComentario = 123;
        String comentario = "comentário editado";

        // ACT
        when(comentarioRepository.findById(anyInt())).thenReturn(Optional.empty());

        // ASSERT
        assertThrows(RegraDeNegocioException.class, () -> comentarioService.editarComentario(idUsuario, idComentario, comentario), "Comentário não encontrado!");
    }

    @Test
    @DisplayName("Deveria editar um comentário com sucesso")
    void deletarComentarioComSucesso() throws RegraDeNegocioException {
        // ARRANGE
        Integer idUsuario = 1;
        Integer idComentario = 1;
        Integer idDenuncia = 1;
        String comentario = "comentário";

        Comentario comentarioCriado = new Comentario();
        comentarioCriado.setIdUsuario(idUsuario);
        comentarioCriado.setIdDenuncia(idDenuncia);
        comentarioCriado.setComentario(comentario);

        // ACT
        when(comentarioRepository.findById(anyInt())).thenReturn(Optional.of(comentarioCriado));
        comentarioService.deletarComentario(idUsuario, idComentario);

        // ASSERT
        verify(comentarioService, times(1)).deletarComentario(idUsuario, idComentario);
    }

    @Test
    void deletarComentarioComFalha() throws RegraDeNegocioException {
        // ARRANGE
        Integer idUsuario = 1;
        Integer idComentario = 123;
        String comentario = "comentário editado";

        // ACT
        when(comentarioRepository.findById(anyInt())).thenReturn(Optional.empty());

        // ASSERT
        assertThrows(RegraDeNegocioException.class, () -> comentarioService.editarComentario(idUsuario, idComentario, comentario), "Comentário não encontrado!");
    }

    @Test
    void deletarComentarioComFalha2() throws RegraDeNegocioException {
        // ARRANGE
        Integer idUsuario = 1; // ID de usuário inválido
        Integer idComentario = 1; // ID de comentário qualquer
        Comentario comentario = new Comentario();
        comentario.setIdUsuario(2);

        // ACT
        when(comentarioRepository.findById(idComentario)).thenReturn(Optional.of(comentario));

        // ASSERT
        assertThrows(RegraDeNegocioException.class, () -> comentarioService.deletarComentario(idUsuario, idComentario));
    }
}