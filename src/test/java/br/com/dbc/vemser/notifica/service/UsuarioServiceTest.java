package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.*;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import br.com.dbc.vemser.notifica.security.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenService tokenService;
    @Mock
    private static Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();

    @InjectMocks
    private UsuarioService usuarioService;
    @Test
    void obterUsuarioById() throws RegraDeNegocioException {
        Optional<Usuario> pessoaEntityMock = Optional.of(usuarioMock());
        UsuarioDTO pessoaDTOMock = usuarioDTOmock();
        Integer idAleatorio = new Random().nextInt();

        when(usuarioRepository.findById(anyInt())).thenReturn(pessoaEntityMock);
        when(objectMapper.convertValue(pessoaEntityMock.get(), UsuarioDTO.class)).thenReturn(pessoaDTOMock);

        UsuarioDTO pessoaDTORetornada =  usuarioService.obterUsuarioById(idAleatorio);

        assertNotNull(pessoaDTORetornada);
        assertEquals(pessoaDTORetornada, pessoaDTOMock);

    }

    @Test
    void atualizarUsuario() throws RegraDeNegocioException {
        Optional<Usuario> usuarioMock = Optional.of(new Usuario());
        usuarioMock.get().setIdUsuario(1);
        usuarioMock.get().setEmailUsuario("email@gmail.com");
        usuarioMock.get().setEtniaUsuario(Etnia.PRETO);
        usuarioMock.get().setNomeUsuario("Maria");
        usuarioMock.get().setGeneroUsuario(Genero.FEMININO);
        usuarioMock.get().setUsuarioAtivo(UsuarioAtivo.SIM);
        usuarioMock.get().setClasseSocial(ClasseSocial.B);
        usuarioMock.get().setNumeroCelular("922222222");
        usuarioMock.get().setTipoUsuario(TipoUsuario.COMUM);
        usuarioMock.get().setSenhaUsuario(argon2PasswordEncoder.encode("Senha123"));

        Usuario usuarioAntigoMock = new Usuario();
        BeanUtils.copyProperties(usuarioMock, usuarioAntigoMock);

        UsuarioUpdateDTO usuarioUpdateDTOMock = usuarioUpdateDTOMock();
        Usuario usuarioEditado = usuarioMock();
        UsuarioDTO pessoaDTOMock = usuarioDTOmock();

        when(usuarioRepository.findById(anyInt())).thenReturn(usuarioMock);
        when(usuarioRepository.save(anyObject())).thenReturn(usuarioEditado);
        when(objectMapper.convertValue(usuarioEditado, UsuarioDTO.class)).thenReturn(pessoaDTOMock);

        UsuarioDTO usuarioDTOretornado = usuarioService.atualizarUsuario(usuarioMock.get().getIdUsuario(), usuarioUpdateDTOMock);

        assertNotNull(usuarioDTOretornado);
        assertNotEquals(usuarioAntigoMock, usuarioMock.get());
        assertNotEquals(usuarioAntigoMock.getEmailUsuario(), usuarioDTOretornado.getEmailUsuario());


    }

    @Test
    void removerUsuario() throws RegraDeNegocioException {
        Integer idAleatorio = new Random().nextInt();
        Optional<Usuario> usuarioDesativadoMock = Optional.of(usuarioMock());
        doReturn(usuarioDesativadoMock).when(usuarioRepository).findById(idAleatorio);

        usuarioService.removerUsuario(idAleatorio);

        assertEquals(usuarioDesativadoMock.get().getUsuarioAtivo(), UsuarioAtivo.NAO);
        assertNotNull(usuarioDesativadoMock);
    }

    @Test
    void attSenha() {
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

    public static UsuarioDTO usuarioDTOmock(){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(1);
        usuarioDTO.setEmailUsuario("emailteste@gmail.com");
        usuarioDTO.setEtniaUsuario(Etnia.BRANCO);
        usuarioDTO.setNomeUsuario("Joao");
        usuarioDTO.setGeneroUsuario(Genero.MASCULINO);
        usuarioDTO.setUsuarioAtivo(UsuarioAtivo.SIM);
        usuarioDTO.setClasseSocial(ClasseSocial.B);
        usuarioDTO.setNumeroCelular("911111111");
        usuarioDTO.setDataNascimento(LocalDate.of(2000,11,20));

        return usuarioDTO;
    }

    public static UsuarioUpdateDTO usuarioUpdateDTOMock(){
        UsuarioUpdateDTO usuario = new UsuarioUpdateDTO();
        usuario.setEmailUsuario("emailteste@gmail.com");
        usuario.setEtniaUsuario(Etnia.BRANCO);
        usuario.setNomeUsuario("Joao");
        usuario.setGeneroUsuario(Genero.MASCULINO);
        usuario.setClasseSocial(ClasseSocial.B);
        usuario.setNumeroCelular("911111111");
        usuario.setDataNascimento(LocalDate.of(2000,12,20));
        return usuario;
    }
}