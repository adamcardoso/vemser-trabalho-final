package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Instituicao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.UsuarioAtivo;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.InstituicaoRepository;
import br.com.dbc.vemser.notifica.repository.LoginRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import br.com.dbc.vemser.notifica.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private InstituicaoRepository instituicaoRepository;

    @Mock
    private Argon2PasswordEncoder argon2PasswordEncoder;

    @InjectMocks
    private LoginService loginService;

    @Test
    void deveriaCriarUmUsuarioComSucesso() throws RegraDeNegocioException {
        UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO();
        usuarioCreateDTO.setEmailUsuario("newuser@email.com");
        usuarioCreateDTO.setNumeroCelular("123456789");
        usuarioCreateDTO.setSenhaUsuario("Password123@");

        when(usuarioRepository.usuarioInativoCadastrado(any(), any())).thenReturn(null);
        when(loginRepository.findByEmailUsuario(usuarioCreateDTO.getEmailUsuario())).thenReturn(Optional.empty());
        when(loginRepository.findByNumeroCelular(usuarioCreateDTO.getNumeroCelular())).thenReturn(Optional.empty());

        Usuario usuarioCriado = new Usuario();
        usuarioCriado.setIdUsuario(1);
        usuarioCriado.setEmailUsuario("newuser@email.com");
        usuarioCriado.setNumeroCelular("123456789");

        String senhaSimulada = argon2PasswordEncoder.encode("Password123@");

        usuarioCriado.setSenhaUsuario(senhaSimulada);
        usuarioCriado.setUsuarioAtivo(UsuarioAtivo.SIM);

        when(objectMapper.convertValue(any(), eq(Usuario.class))).thenReturn(usuarioCriado);
        when(objectMapper.convertValue(any(), eq(UsuarioDTO.class))).thenReturn(new UsuarioDTO());
        when(usuarioRepository.save(usuarioCriado)).thenReturn(usuarioCriado);

        UsuarioDTO result = loginService.createUsuario(usuarioCreateDTO);

        assertNotNull(result);
        assertEquals(UsuarioAtivo.SIM, usuarioCriado.getUsuarioAtivo());

    }
    @Test
    void deveriaReativarUsuarioDesativado() throws RegraDeNegocioException {
        UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO();
        usuarioCreateDTO.setEmailUsuario("existinguser@email.com");
        usuarioCreateDTO.setNumeroCelular("123456789");
        usuarioCreateDTO.setSenhaUsuario("Password123@");

        Usuario usuarioDesativado = new Usuario();
        usuarioDesativado.setUsuarioAtivo(UsuarioAtivo.NAO);

        when(usuarioRepository.usuarioInativoCadastrado(any(), any())).thenReturn(usuarioDesativado);

        Usuario usuarioCriado = new Usuario();
        usuarioCriado.setIdUsuario(1);
        usuarioCriado.setEmailUsuario("existinguser@email.com");
        usuarioCriado.setNumeroCelular("123456789");
        usuarioCriado.setSenhaUsuario(argon2PasswordEncoder.encode("Password123@"));
        usuarioCriado.setUsuarioAtivo(UsuarioAtivo.SIM);

        when(objectMapper.convertValue(any(), eq(UsuarioDTO.class))).thenReturn(new UsuarioDTO());
        when(usuarioRepository.save(usuarioDesativado)).thenReturn(usuarioCriado);

        UsuarioDTO result = loginService.createUsuario(usuarioCreateDTO);

        assertNotNull(result);
        assertEquals(UsuarioAtivo.SIM, usuarioDesativado.getUsuarioAtivo());
    }


    @Test
    void deveriaFalharAoCriarUsuarioComEmailExistente() {
        UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO();
        usuarioCreateDTO.setEmailUsuario("existinguser@email.com");
        usuarioCreateDTO.setNumeroCelular("123456789");
        usuarioCreateDTO.setSenhaUsuario("Password123@");

        when(loginRepository.findByEmailUsuario(usuarioCreateDTO.getEmailUsuario()))
                .thenReturn(Optional.of(new Usuario()));

        assertThrows(RegraDeNegocioException.class,
                () -> loginService.createUsuario(usuarioCreateDTO),
                "Credenciais inválidas, Usuário Já Cadastrado!");
    }

    @Test
    void deveriaFalharAoCriarUsuarioComNumeroCelularExistente() {
        UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO();
        usuarioCreateDTO.setEmailUsuario("newuser@email.com");
        usuarioCreateDTO.setNumeroCelular("existingNumber");
        usuarioCreateDTO.setSenhaUsuario("Password123@");

        when(loginRepository.findByNumeroCelular(usuarioCreateDTO.getNumeroCelular()))
                .thenReturn(Optional.of(new Usuario()));

        assertThrows(RegraDeNegocioException.class,
                () -> loginService.createUsuario(usuarioCreateDTO),
                "Esse Número Já Está Cadastrado!");
    }

    @Test
    void deveriaRetornarUsuarioPeloId() {
        int idUsuario = 1;
        Usuario mockUsuario = new Usuario();
        when(loginRepository.findByIdUsuario(idUsuario)).thenReturn(Optional.of(mockUsuario));

        Optional<Usuario> result = loginService.findById(idUsuario);

        assertTrue(result.isPresent());
        assertEquals(mockUsuario, result.get());
    }

    @Test
    void deveriaRetornarInstituicaoPeloId() {
        int idInstituicao = 1;
        Instituicao mockInstituicao = new Instituicao();
        when(instituicaoRepository.findByIdInstituicao(idInstituicao)).thenReturn(Optional.of(mockInstituicao));

        Optional<Instituicao> result = loginService.findByIdIntituicao(idInstituicao);

        assertTrue(result.isPresent());
        assertEquals(mockInstituicao, result.get());
    }

    @Test
    void deveriaRetornarUsuarioPeloEmail() {
        String emailUsuario = "renata.schafer@dbccompany.com.br";
        Usuario mockUsuario = new Usuario();
        when(loginRepository.findByEmailUsuario(emailUsuario)).thenReturn(Optional.of(mockUsuario));

        Optional<Usuario> result = loginService.findByEmailUsuario(emailUsuario);

        assertTrue(result.isPresent());
        assertEquals(mockUsuario, result.get());
    }

    @Test
    void deveriaRetornarIdDoUsuarioLogado() {
        int idUsuarioLogado = 1;
        mockAuthenticatedUser(idUsuarioLogado);

        int result = loginService.getIdLoggedUser();

        assertEquals(idUsuarioLogado, result);
    }

    @Test
    void deveriaRetornarUsuarioLogado() throws RegraDeNegocioException {
        int idUsuarioLogado = 1;
        mockAuthenticatedUser(idUsuarioLogado);
        Usuario mockUsuario = new Usuario();
        when(loginRepository.findByIdUsuario(idUsuarioLogado)).thenReturn(Optional.of(mockUsuario));

        Usuario result = loginService.getLoggedUser();

        assertEquals(mockUsuario, result);
    }

    @Test
    void deveriaRetornarInstituicaoPeloEmail() {
        String emailInstituicao = "testinstituicao@email.com";
        Instituicao mockInstituicao = new Instituicao();
        when(instituicaoRepository.findByEmailInstituicao(emailInstituicao)).thenReturn(Optional.of(mockInstituicao));

        Optional<Instituicao> result = loginService.findByEmailInstituicao(emailInstituicao);

        assertTrue(result.isPresent());
        assertEquals(mockInstituicao, result.get());
    }

    @Test
    void deveriaRetornarInstituicaoLogada() throws RegraDeNegocioException {
        int idInstituicaoLogada = 1;
        mockAuthenticatedUser(idInstituicaoLogada);
        Instituicao mockInstituicao = new Instituicao();
        when(instituicaoRepository.findByIdInstituicao(idInstituicaoLogada)).thenReturn(Optional.of(mockInstituicao));

        Instituicao result = loginService.getLoggedInstituicao();

        assertEquals(mockInstituicao, result);
    }

    private void mockAuthenticatedUser(int id) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = mock(Authentication.class);

        when(authentication.getPrincipal()).thenReturn(String.valueOf(id));
        securityContext.setAuthentication(authentication);
    }

}