package br.com.dbc.vemser.notifica.service;

import static org.mockito.ArgumentMatchers.anyInt;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.instituicao.InstitucaoCreateDTO;
import br.com.dbc.vemser.notifica.dto.instituicao.InstituicaoDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.admin_dto.DenunciaListDTO;
import br.com.dbc.vemser.notifica.dto.usuario.admin_dto.UsuarioListDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Instituicao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.*;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.*;
import br.com.dbc.vemser.notifica.security.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurtidaServiceTest {
    @Mock
    private CurtidaRepository curtidaRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ComentarioRepository comentarioRepository;
    @Mock
    private DenunciaRepository denunciaRepository;

    @InjectMocks
    private CurtidaService curtidaService;

    private static Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();
    @Test
    void apoiarDenuncia() throws RegraDeNegocioException {
        Optional<Usuario> usuarioMock = Optional.of(usuarioMock());
        Denuncia denunciaMock = denunciaMock();
        Integer numeroCurtida = denunciaMock.getNumeroCurtidas();
        when(denunciaRepository.getDenunciaAtiva(anyInt())).thenReturn(denunciaMock);
        when(usuarioRepository.findById(anyInt())).thenReturn(usuarioMock);
        curtidaService.apoiarDenuncia(usuarioMock.get().getIdUsuario(),denunciaMock.getIdDenuncia());
        assertNotEquals(numeroCurtida, denunciaMock.getNumeroCurtidas());

    }

    @Test
    void apoiarComentario() throws RegraDeNegocioException {
        Optional<Usuario> usuarioMock = Optional.of(usuarioMock());
        Optional<Comentario> comentarioMock = Optional.of(comentarioMock());
        Integer numeroCurtida = comentarioMock.get().getNumeroCurtidas();
        when(comentarioRepository.findById(anyInt())).thenReturn(comentarioMock);
        when(usuarioRepository.findById(anyInt())).thenReturn(usuarioMock);
        curtidaService.apoiarComentario(usuarioMock.get().getIdUsuario(),comentarioMock.get().getIdComentario());
        assertNotEquals(numeroCurtida, comentarioMock.get().getNumeroCurtidas());
    }

    @Test
    public void deveriaRetornarExceptionAoReceberIdNaoExistente() {
        Integer idNaoExistente = new Random().nextInt();

        assertThrows(RegraDeNegocioException.class, () -> curtidaService.apoiarComentario(idNaoExistente, idNaoExistente));
        assertThrows(RegraDeNegocioException.class, () -> curtidaService.apoiarDenuncia(idNaoExistente, idNaoExistente));

    }

    public static Usuario usuarioMock(){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setEmailUsuario("emailteste@gmail.com");
        usuario.setEtniaUsuario(Etnia.BRANCO);
        usuario.setNomeUsuario("Joao");
        usuario.setGeneroUsuario(Genero.MASCULINO);
        usuario.setUsuarioAtivo(UsuarioAtivo.SIM);
        usuario.setClasseSocial(ClasseSocial.B);
        usuario.setNumeroCelular("911111111");
        usuario.setTipoUsuario(TipoUsuario.COMUM);
        usuario.setSenhaUsuario(argon2PasswordEncoder.encode("Senha123@"));
        usuario.setDataNascimento(LocalDate.of(2000,12,20));
        return usuario;
    }


    public static Denuncia denunciaMock(){
        Denuncia denuncia = new Denuncia();
        denuncia.setUsuario(usuarioMock());
        denuncia.setStatusDenuncia(StatusDenuncia.EM_ANDAMENTO);
        denuncia.setIdDenuncia(1);
        denuncia.setNumeroCurtidas(2);
        denuncia.setTipoDenuncia(TipoDenuncia.PUBLICA);
        denuncia.setDataHora(LocalDateTime.now());
        denuncia.setTitulo("Denuncia teste");

        return denuncia;
    }

    public static Comentario comentarioMock(){
        Comentario comentario = new Comentario();

        comentario.setIdComentario(1);
        comentario.setUsuario(usuarioMock());
        comentario.setDenuncia(denunciaMock());
        comentario.setComentario("Comentario Teste");
        comentario.setNumeroCurtidas(1);

        return comentario;
    }
}