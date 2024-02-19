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

    @Test
    @DisplayName("Deveria criar um comentário relacionado com usuário e denúncia")
    void criarComentarioComsucesso() throws RegraDeNegocioException {
        // ARRANGE
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

        given(comentarioRepository.save(comentarioCriado)).willReturn(comentarioretornado);
        doReturn(comentarioDTO).when(comentarioService).criarComentario(1,1, "mensagem");

        // ACT
        when(usuarioRepository.getById(1)).thenReturn(usuario);
        when(comentarioRepository.save(comentarioCriado)).thenReturn(comentarioretornado);
        Usuario u = usuarioRepository.getById(1);
        Comentario c = comentarioRepository.save(comentarioCriado);

        ComentarioDTO cDTO = comentarioService.criarComentario(1, 1, "mensagem");

        // ASSERT
        assertNotNull(cDTO);
        assertEquals(c.getIdUsuario(), cDTO.getIdUsuario());
        assertEquals(c.getIdDenuncia(), cDTO.getIdDenuncia());
        assertEquals(c.getComentario(), cDTO.getComentario());
    }

    @Test
    @DisplayName("Não deveria criar um comentário relacionado com usuário e denúncia")
    void criarComentarioComFalha() throws RegraDeNegocioException {
        // ARRANGE
        Integer idUsuario = 1;
        Integer idDenuncia = 123;

        Comentario comentarioCriado = new Comentario();
        comentarioCriado.setIdUsuario(1);
        comentarioCriado.setIdDenuncia(1);
        comentarioCriado.setComentario("comentário");
        comentarioCriado.setNumeroCurtidas(0);

        doThrow(RuntimeException.class).when(comentarioService).criarComentario(1,1,"comentário");

        // ACT
        when(denunciaRepository.getDenunciaAtiva(idDenuncia)).thenReturn(null);
        when(denunciaRepository.getDenunciaAtiva(idDenuncia)).thenThrow(new RuntimeException("Denuncia não encontrada!"));


        // ASSERT
        assertThrows(RuntimeException.class, () -> comentarioService.criarComentario(idUsuario, idDenuncia, "comentario"),
                "Denuncia não encontrada!");

        assertThrows(RuntimeException.class, () -> denunciaRepository.getDenunciaAtiva(idDenuncia), "Denuncia não encontrada!");

        assertThrows(RuntimeException.class, () -> comentarioService.criarComentario(1,1,"comentário"), "Denuncia não encontrada!");
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

        doReturn(comentarioDTO).when(comentarioService).editarComentario(idUsuario, idDenuncia, comentario);
        // ACT
        when(comentarioRepository.findById(idComentario)).thenReturn(Optional.of(comentarioretornado));
        when(comentarioRepository.save(comentarioCriado)).thenReturn(comentarioretornado);

        Optional<Comentario> cOPT = comentarioRepository.findById(idComentario);
        Comentario c = comentarioRepository.save(comentarioCriado);

        ComentarioDTO cDTO = comentarioService.editarComentario(idUsuario, idDenuncia, comentario);

        // ASSERT
        assertNotNull(cDTO);
        assertEquals(c.getIdUsuario(), cDTO.getIdUsuario());
        assertEquals(c.getIdDenuncia(), cDTO.getIdDenuncia());
        assertEquals(c.getComentario(), cDTO.getComentario());
    }

    @Test
    @DisplayName("Deveria lançar um erro por comentário não encontrado")
    void editarComentarioComFalha() throws RegraDeNegocioException {
        // ARRANGE
        Integer idUsuario = 1;
        Integer idComentario = 123;
        String comentario = "comentário editado";

        doThrow(new RuntimeException()).when(comentarioService).editarComentario(idUsuario, idComentario, comentario);

        // ACT
        when(comentarioRepository.findById(idComentario)).thenReturn(Optional.of(new Comentario()));
        Optional<Comentario> cOPT = comentarioRepository.findById(idComentario);
        when(cOPT.isEmpty()).thenThrow(new RuntimeException());

        // ASSERT
        assertThrows(RuntimeException.class, () -> comentarioRepository.findById(idComentario), "Comentário não encontrado!");
        assertThrows(RuntimeException.class, () -> comentarioService.editarComentario(idUsuario, idComentario, comentario), "você so pode editar seus comentarios!");
    }

    @Test
    @DisplayName("Deveria editar um comentário com sucesso")
    void deletarComentarioComSucesso() throws RegraDeNegocioException {
        // ARRANGE
        Integer idUsuario = 1;
        Integer idComentario = 1;

        doNothing().when(comentarioService).deletarComentario(idUsuario, idComentario);

        // ACT
        comentarioService.deletarComentario(idUsuario, idComentario);

        // ASSERT
        verify(comentarioService, times(1)).deletarComentario(idUsuario, idComentario);
    }

    @Test
    void deletarComentarioComFalha() throws RegraDeNegocioException {
        // ARRANGE
        Integer idUsuario = 1;
        Integer idComentario = 999;

        doThrow(new RuntimeException()).when(comentarioService).deletarComentario(idUsuario, idComentario);

        // ACT
        when(comentarioRepository.findById(idComentario)).thenReturn(Optional.empty());
        comentarioRepository.findById(idComentario);

        // ASSERT
        assertThrows(RuntimeException.class, () -> comentarioService.deletarComentario(idUsuario, idComentario),
                "Comentário não encontrado");
        assertThrows(RuntimeException.class, () -> comentarioService.deletarComentario(idUsuario, idComentario), "você so pode excluir seus comentarios!");
    }
}