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
import br.com.dbc.vemser.notifica.repository.AdminRepository;
import br.com.dbc.vemser.notifica.repository.ComentarioRepository;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.InstituicaoRepository;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
class AdminServiceTest {
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private DenunciaRepository denunciaRepository;
    @Mock
    private ComentarioRepository comentarioRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private static Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();
    @Mock
    private InstituicaoRepository instituicaoRepository;
    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AdminService adminService;

    @Test
    public void deveriaCriarAdminComSucesso(){
        UsuarioCreateDTO usuarioCreateMockDTO = usuarioCreateDTOMock();
        UsuarioDTO usuarioMockDTO = usuarioDTOmock();
        Usuario usuarioMock = usuarioMock();

        when(objectMapper.convertValue(usuarioCreateMockDTO, Usuario.class)).thenReturn(usuarioMock);
        when(adminRepository.save(any())).thenReturn(usuarioMock);
        when(objectMapper.convertValue(usuarioMock, UsuarioDTO.class)).thenReturn(usuarioMockDTO);

        UsuarioDTO pessoaDTOCriada =  adminService.criarUsuarioAdmin(usuarioCreateMockDTO);

        assertNotNull(pessoaDTOCriada);
        assertEquals(pessoaDTOCriada, usuarioMockDTO);

    }


    @Test
    public void deveriaRetornarPessoaDTOPorId() throws RegraDeNegocioException {
        Optional<Usuario> pessoaEntityMock = Optional.of(usuarioMock());
        UsuarioDTO pessoaDTOMock = usuarioDTOmock();
        Integer idAleatorio = new Random().nextInt();

        when(adminRepository.findById(anyInt())).thenReturn(pessoaEntityMock);
        when(objectMapper.convertValue(pessoaEntityMock.get(), UsuarioDTO.class)).thenReturn(pessoaDTOMock);

        UsuarioDTO pessoaDTORetornada =  adminService.obterUsuarioById(idAleatorio);

        assertNotNull(pessoaDTORetornada);
        assertEquals(pessoaDTORetornada, pessoaDTOMock);

    }

    @Test
    public void deveriaRetornarListaAdminComSucesso(){
        List<Usuario> listaMock = List.of(usuarioAdminMock(), usuarioMock(), usuarioMock());
        when(adminRepository.usuariosAdmin()).thenReturn(listaMock);
        List<UsuarioListDTO> listAdmins = adminService.listUsuariosAdmin();

        assertNotNull(listAdmins);
        assertEquals(listaMock.size(), listAdmins.size());
    }

    @Test
    public void deveriaRetornarListaUsuarioComSucesso(){
        List<Usuario> listaMock = List.of(usuarioAdminMock(), usuarioMock(), usuarioMock());
        when(adminRepository.listAll()).thenReturn(listaMock);
        List<UsuarioListDTO> listAdmins = adminService.listAll();

        assertNotNull(listAdmins);
        assertEquals(listaMock.size(), listAdmins.size());
    }

    @Test
    public void deveriaDesativarUsuarioComSucesso(){
        Integer idAleatorio = new Random().nextInt();
        Usuario usuarioDesativadoMock = usuarioMock();
        doReturn(usuarioDesativadoMock).when(adminRepository).getUsuarioAtivo(idAleatorio);

        adminService.removerUsuario(idAleatorio);

        assertEquals(usuarioDesativadoMock.getUsuarioAtivo(), UsuarioAtivo.NAO);
        assertNotNull(usuarioDesativadoMock);
    }

    @Test
    public void deveriaEditarPessoaComSucesso() throws Exception {
        Usuario usuarioMock = new Usuario();
        usuarioMock.setIdUsuario(1);
        usuarioMock.setEmailUsuario("email@gmail.com");
        usuarioMock.setEtniaUsuario(Etnia.PRETO);
        usuarioMock.setNomeUsuario("Maria");
        usuarioMock.setGeneroUsuario(Genero.FEMININO);
        usuarioMock.setUsuarioAtivo(UsuarioAtivo.SIM);
        usuarioMock.setClasseSocial(ClasseSocial.B);
        usuarioMock.setNumeroCelular("922222222");
        usuarioMock.setTipoUsuario(TipoUsuario.COMUM);
        usuarioMock.setSenhaUsuario(argon2PasswordEncoder.encode("Senha123"));

        Usuario usuarioAntigoMock = new Usuario();
        BeanUtils.copyProperties(usuarioMock, usuarioAntigoMock);

        UsuarioUpdateDTO usuarioUpdateDTOMock = usuarioUpdateDTOMock();
        Usuario usuarioEditado = usuarioMock();
        UsuarioDTO pessoaDTOMock = usuarioDTOmock();

        when(adminRepository.getUsuarioAtivo(anyInt())).thenReturn(usuarioMock);
        when(adminRepository.save(anyObject())).thenReturn(usuarioEditado);
        when(objectMapper.convertValue(usuarioEditado, UsuarioDTO.class)).thenReturn(pessoaDTOMock);

        UsuarioDTO usuarioDTOretornado = adminService.atualizarUsuario(usuarioMock.getIdUsuario(), usuarioUpdateDTOMock);

        assertNotNull(usuarioDTOretornado);
        assertNotEquals(usuarioAntigoMock, usuarioMock);
        assertNotEquals(usuarioAntigoMock.getEmailUsuario(), usuarioDTOretornado.getEmailUsuario());
    }

//    @Test
//    public void deveriaTrocaSenhaComSucesso() throws RegraDeNegocioException {
//        // Arrange
//        Usuario usuarioMock = usuarioMock();
//        String senhaAntiga = usuarioMock.getSenhaUsuario();
//        when(adminRepository.getUsuarioAtivo(anyInt())).thenReturn(usuarioMock);
//
//        // Mocking the authentication manager
//        UsernamePasswordAuthenticationToken authRequest =
//                new UsernamePasswordAuthenticationToken(
//                        usuarioMock.getEmailUsuario(),
//                        "Senha123@"
//                );
//        Authentication authentication = mock(Authentication.class);
//        when(authenticationManager.authenticate(authRequest)).thenReturn(authentication);
//
//        // Mocking the argon2PasswordEncoder
//        when(argon2PasswordEncoder.encode("senha123")).thenReturn("hashedNewPassword");
//
//        // Mocking the adminRepository.save method
//        when(adminRepository.save(usuarioMock)).thenReturn(usuarioMock);
//
//        // Mocking the tokenService
//        when(tokenService.generateToken(usuarioMock)).thenReturn("generatedToken");
//
//        // Act
//        String trocasenha = adminService.attSenha(usuarioMock.getIdUsuario(), "Senha123@", "senha123");
//
//        // Assert
//        assertNotEquals(senhaAntiga, usuarioMock.getSenhaUsuario());
//        assertNotNull(usuarioMock.getSenhaUsuario());
//        assertEquals("generatedToken", trocasenha);
//
//        // Verify interactions
//        verify(adminRepository, times(1)).getUsuarioAtivo(anyInt());
//        verify(authenticationManager, times(1)).authenticate(authRequest);
//        verify(argon2PasswordEncoder, times(1)).encode("senha123");
//        verify(adminRepository, times(1)).save(usuarioMock);
//        verify(tokenService, times(1)).generateToken(usuarioMock);
//    }

    @Test
    public void deveriaRetornarListaDenunciaAtivaoComSucesso(){
        List<Denuncia> listaMock = List.of(denunciaMock(), denunciaMock(), denunciaMock());
        when(denunciaRepository.getDenuncias()).thenReturn(listaMock);
        List<DenunciaListDTO> listDenunciaAtiva = adminService.listarTodasDenunciasAtivas();

        assertNotNull(listDenunciaAtiva);
        assertEquals(listaMock.size(), listDenunciaAtiva.size());
    }

    @Test
    public void deveriaDenunciaPorId() throws RegraDeNegocioException {
        Denuncia denunciaMock = denunciaMock();
        DenunciaDTO denunciaDTOMock = denunciaDTOMock();
        Integer idAleatorio = new Random().nextInt();

        when(denunciaRepository.getDenunciaAtiva(anyInt())).thenReturn(denunciaMock);
        when(objectMapper.convertValue(denunciaMock, DenunciaDTO.class)).thenReturn(denunciaDTOMock);

        DenunciaDTO denunciaDTORetornada=  adminService.denunciaById(idAleatorio);

        assertNotNull(denunciaDTORetornada);
        assertEquals(denunciaDTORetornada, denunciaDTOMock);

    }

    @Test
    public void deveriaDesativarDenucniaComSucesso() throws RegraDeNegocioException {
        Integer idAleatorio = new Random().nextInt();
        Denuncia denunciaDesativadaMock = denunciaMock();
        doReturn(denunciaDesativadaMock).when(denunciaRepository).getDenunciaAtiva(idAleatorio);

        adminService.deletarDenuncia(idAleatorio);

        assertEquals(denunciaDesativadaMock.getStatusDenuncia(), StatusDenuncia.FECHADO);
        assertNotNull(denunciaDesativadaMock);
    }

    @Test
    public void deveriaDeletarComentarioComSucesso() {
        Integer idAleatorio = new Random().nextInt();
        Optional<Comentario> comentarioDesativado = Optional.of(comentarioMock());
        doReturn(comentarioDesativado).when(comentarioRepository).findById(eq(idAleatorio));

        adminService.deletarComentario(idAleatorio);

        verify(comentarioRepository, times(1)).delete(comentarioDesativado.get());
    }

    @Test
    public void deveriaRetornarExceptionAoReceberIdNaoExistente() {
        Integer idNaoExistente = new Random().nextInt();

        assertThrows(RegraDeNegocioException.class, () -> adminService.denunciaById(idNaoExistente));
        assertThrows(RegraDeNegocioException.class, () -> adminService.obterUsuarioById(idNaoExistente));

    }

    @Test
    public void deveriaCriarInstituicaoComSucesso() throws RegraDeNegocioException {
        InstitucaoCreateDTO instituicaoCreateMockDTO = instituicaoCreateDTOMock();
        InstituicaoDTO instituicaoMockDTO = instituicaoDTOMock();
        Instituicao instituicaoMock = instituicaoMock();

        when(objectMapper.convertValue(instituicaoCreateMockDTO, Instituicao.class)).thenReturn(instituicaoMock);
        when(instituicaoRepository.save(any())).thenReturn(instituicaoMock);
        when(objectMapper.convertValue(instituicaoMock, InstituicaoDTO.class)).thenReturn(instituicaoMockDTO);

        InstituicaoDTO instituicaoDTOCriada =  adminService.criarUsuarioInstitucao(instituicaoCreateMockDTO);

        assertNotNull(instituicaoDTOCriada);
        assertEquals(instituicaoDTOCriada, instituicaoMockDTO);

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


    public static Usuario usuarioAdminMock(){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setEmailUsuario("emailteste@gmail.com");
        usuario.setEtniaUsuario(Etnia.BRANCO);
        usuario.setNomeUsuario("Joao");
        usuario.setGeneroUsuario(Genero.MASCULINO);
        usuario.setUsuarioAtivo(UsuarioAtivo.SIM);
        usuario.setClasseSocial(ClasseSocial.B);
        usuario.setNumeroCelular("911111111");
        usuario.setTipoUsuario(TipoUsuario.ADMIN);
        usuario.setSenhaUsuario(argon2PasswordEncoder.encode("Senha123@"));
        usuario.setDataNascimento(LocalDate.of(2000,12,20));
        return usuario;
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
    public static UsuarioCreateDTO usuarioCreateDTOMock(){
        UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO();

        usuarioCreateDTO.setEmailUsuario("emailteste@gmail.com");
        usuarioCreateDTO.setEtniaUsuario(Etnia.BRANCO);
        usuarioCreateDTO.setNomeUsuario("Joao");
        usuarioCreateDTO.setGeneroUsuario(Genero.MASCULINO);
        usuarioCreateDTO.setClasseSocial(ClasseSocial.B);
        usuarioCreateDTO.setNumeroCelular("911111111");
        usuarioCreateDTO.setSenhaUsuario("Senha123@");
        usuarioCreateDTO.setDataNascimento(LocalDate.of(2000,12,20));
        return usuarioCreateDTO;
    }

    public static UsuarioListDTO usuarioListDTOMock(){
        UsuarioListDTO usuarioListDTO = new UsuarioListDTO();
        usuarioListDTO.setIdUsuario(1);
        usuarioListDTO.setEmailUsuario("emailteste@gmail.com");
        usuarioListDTO.setNomeUsuario("Joao");
        usuarioListDTO.setUsuarioAtivo(UsuarioAtivo.SIM);
        usuarioListDTO.setNumeroCelular("911111111");
        return usuarioListDTO;
    }

    public static Denuncia denunciaMock(){
        Denuncia denuncia = new Denuncia();
        denuncia.setUsuario(usuarioMock());
        denuncia.setStatusDenuncia(StatusDenuncia.EM_ANDAMENTO);
        denuncia.setIdDenuncia(1);
        denuncia.setNumeroCurtidas(1);
        denuncia.setTipoDenuncia(TipoDenuncia.PUBLICA);
        denuncia.setDataHora(LocalDateTime.now());
        denuncia.setTitulo("Denuncia teste");

        return denuncia;
    }

    public static DenunciaDTO denunciaDTOMock(){
        DenunciaDTO denunciaDTO = new DenunciaDTO();
        denunciaDTO.setUsuario(usuarioDTOmock());
        denunciaDTO.setStatusDenuncia(StatusDenuncia.EM_ANDAMENTO);
        denunciaDTO.setIdDenuncia(1);
        denunciaDTO.setNumeroCurtidas(1);
        denunciaDTO.setTipoDenuncia(TipoDenuncia.PUBLICA);
        denunciaDTO.setDataHora(LocalDateTime.now());
        denunciaDTO.setTitulo("Denuncia teste");

        return denunciaDTO;
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

    public static ComentarioDTO comentarioDTOMock(){
        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setIdComentario(1);
        comentarioDTO.setUsuario(usuarioDTOmock());
        comentarioDTO.setDenuncia(denunciaDTOMock());
        comentarioDTO.setComentario("Comentario Teste");

        return comentarioDTO;
    }

    public static Instituicao instituicaoMock(){
        Instituicao instituicao = new Instituicao();
        instituicao.setTipoUsuario(TipoUsuario.INSTITUICAO);
        instituicao.setCelularInstituicao("999999999");
        instituicao.setSenhaInstituicao(argon2PasswordEncoder.encode("Senha123@"));
        instituicao.setIdInstituicao(1);
        instituicao.setEmailInstituicao("instituicao@gmail.com");
        instituicao.setNomeInstituicao("Prefeitura");

        return instituicao;
    }

    public static InstitucaoCreateDTO instituicaoCreateDTOMock(){
        InstitucaoCreateDTO instituicao = new InstitucaoCreateDTO();
        instituicao.setCelularInstituicao("999999999");
        instituicao.setSenhaInstituicao(argon2PasswordEncoder.encode("Senha123@"));
        instituicao.setEmailInstituicao("instituicao@gmail.com");
        instituicao.setNomeInstituicao("Prefeitura");

        return instituicao;
    }
    public static InstituicaoDTO instituicaoDTOMock(){
        InstituicaoDTO instituicao = new InstituicaoDTO();
        instituicao.setCelularInstituicao("999999999");
        instituicao.setIdInstituicao(1);
        instituicao.setEmailInstituicao("instituicao@gmail.com");
        instituicao.setNomeInstituicao("Prefeitura");

        return instituicao;
    }
}