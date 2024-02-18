package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.instituicao.InstitucaoCreateDTO;
import br.com.dbc.vemser.notifica.dto.instituicao.InstituicaoDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.admin_dto.DenunciaListDTO;
import br.com.dbc.vemser.notifica.dto.usuario.admin_dto.UsuarioListDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Instituicao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import br.com.dbc.vemser.notifica.entity.enums.UsuarioAtivo;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.AdminRepository;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.ComentarioRepository;
import br.com.dbc.vemser.notifica.repository.InstituicaoRepository;
import br.com.dbc.vemser.notifica.security.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final DenunciaRepository denunciaRepository;
    private final ComentarioRepository comentarioRepository;
    private final AuthenticationManager authenticationManager;
    private final Argon2PasswordEncoder argon2PasswordEncoder;
    private final InstituicaoRepository instituicaoRepository;
    private final TokenService tokenService;
    private final ObjectMapper objectMapper;

    public List<UsuarioListDTO> listUsuariosAdmin(){
        return adminRepository.usuariosAdmin().stream()
                .map(usuario -> retornarListDTO(usuario))
                .collect(Collectors.toList());
    }

    public List<UsuarioListDTO> listAll(){
        return adminRepository.listAll().stream()
                .map(usuario -> retornarListDTO(usuario))
                .collect(Collectors.toList());
    }

    public UsuarioDTO obterUsuarioById(Integer idUsuario) throws RegraDeNegocioException {
        return retornarDTO(getusuario(idUsuario));
    }

    public UsuarioDTO criarUsuarioAdmin(UsuarioCreateDTO novoUsuario) {
        Usuario usuarioDesativado = adminRepository.usuarioInativoCadastrado(novoUsuario.getNumeroCelular(),
                novoUsuario.getEmailUsuario());
        if(usuarioDesativado != null){
            usuarioDesativado.setUsuarioAtivo(UsuarioAtivo.SIM);
            return retornarDTO(adminRepository.save(usuarioDesativado));
        }
        Usuario usuarioCriado = converterDTO(novoUsuario);
        usuarioCriado.setSenhaUsuario(argon2PasswordEncoder.encode(usuarioCriado.getSenhaUsuario()));
        usuarioCriado.setTipoUsuario(TipoUsuario.ADMIN);
        usuarioCriado.setUsuarioAtivo(UsuarioAtivo.SIM);
        return retornarDTO(adminRepository.save(usuarioCriado));
    }

    public InstituicaoDTO criarUsuarioInstitucao(InstitucaoCreateDTO novaInstituicao) throws RegraDeNegocioException {
        for (Instituicao i: instituicaoRepository.findAll()){
            if (i.getEmailInstituicao().equals(novaInstituicao.getEmailInstituicao())){
                throw new RegraDeNegocioException("Email ja cadastrado!");
            }
            if (i.getCelularInstituicao().equals(novaInstituicao.getCelularInstituicao())){
                throw new RegraDeNegocioException("Celular ja cadastrado!");
            }
        }
        Instituicao instituicao = objectMapper.convertValue(novaInstituicao, Instituicao.class);
        instituicao.setSenhaInstituicao(argon2PasswordEncoder.encode(instituicao.getSenhaInstituicao()));
        instituicao.setTipoUsuario(TipoUsuario.INSTITUICAO);
        instituicaoRepository.save(instituicao);
        return objectMapper.convertValue(instituicao, InstituicaoDTO.class);
    }

    public UsuarioDTO atualizarUsuario(Integer idUsuario, UsuarioUpdateDTO novoUsuario) throws Exception {
        Usuario usuarioRecuperado = getUsuarioAtivo(idUsuario);
        usuarioRecuperado.setEmailUsuario(novoUsuario.getEmailUsuario());
        usuarioRecuperado.setEtniaUsuario(novoUsuario.getEtniaUsuario());
        usuarioRecuperado.setGeneroUsuario(novoUsuario.getGeneroUsuario());
        usuarioRecuperado.setNomeUsuario(novoUsuario.getNomeUsuario());
        usuarioRecuperado.setClasseSocial(novoUsuario.getClasseSocial());
        usuarioRecuperado.setDataNascimento(novoUsuario.getDataNascimento());
        usuarioRecuperado.setNumeroCelular(novoUsuario.getNumeroCelular());

        return retornarDTO(adminRepository.save(usuarioRecuperado));
    }

    public String attSenha(Integer idUsuario, String senha, String novaSenha) throws RegraDeNegocioException {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            getUsuarioAtivo(idUsuario).getEmailUsuario(),
                            senha
                    );

            Authentication authentication =
                    authenticationManager.authenticate(
                            usernamePasswordAuthenticationToken);

            Usuario usuarioValidado = (Usuario) authentication.getPrincipal();
            usuarioValidado.setSenhaUsuario(argon2PasswordEncoder.encode(novaSenha));
            adminRepository.save(usuarioValidado);
            return tokenService.generateToken(usuarioValidado);
        } catch (AuthenticationException ex) {
            throw new RegraDeNegocioException("Senha incorreta!");
        }
    }

    public void removerUsuario(Integer idUsuario) {
        Usuario usuarioDeletado = adminRepository.getUsuarioAtivo(idUsuario);
        usuarioDeletado.setUsuarioAtivo(UsuarioAtivo.NAO);
        adminRepository.save(usuarioDeletado);
    }

    public List<DenunciaListDTO> listarTodasDenunciasAtivas(){
        return denunciaRepository.getDenuncias().stream()
                .map(denuncia -> retornaListDenunciaDTO(denuncia))
                .collect(Collectors.toList());
    }

    public DenunciaDTO denunciaById(Integer id) throws RegraDeNegocioException {
        return retornarDTODenuncia(getDenuncia(id));
    }

    public void deletarDenuncia(Integer id) throws RegraDeNegocioException {
        Denuncia denunciaDeletada = getDenuncia(id);
        denunciaDeletada.setStatusDenuncia(StatusDenuncia.FECHADO);
        denunciaRepository.save(denunciaDeletada);
    }

    public void deletarComentario(Integer idComentario){
        Comentario comentarioExcluido = comentarioRepository.findById(idComentario).get();
        comentarioRepository.delete(comentarioExcluido);
    }

    private Usuario getUsuarioAtivo(Integer id) throws RegraDeNegocioException {
        Usuario usuario = adminRepository.getUsuarioAtivo(id);
        if (usuario == null) {
            throw new RegraDeNegocioException("Usuário não encontrado!");
        }
        return usuario;
    }

    private Usuario getusuario(Integer idUsuario) throws RegraDeNegocioException {
        return adminRepository.findById(idUsuario)
                .orElseThrow(() -> new RegraDeNegocioException("Usuario não encontrado!"));
    }

    private Usuario converterDTO(UsuarioCreateDTO dto) {
        return objectMapper.convertValue(dto, Usuario.class);
    }

    private UsuarioDTO retornarDTO(Usuario entity) {
        return objectMapper.convertValue(entity, UsuarioDTO.class);
    }

    private UsuarioListDTO retornarListDTO(Usuario entity) {
        return objectMapper.convertValue(entity, UsuarioListDTO.class);
    }

    private Denuncia getDenuncia(Integer id) throws RegraDeNegocioException {
        Denuncia denuncia = denunciaRepository.getDenunciaAtiva(id);
        if (denuncia == null){
            throw new RegraDeNegocioException("Denuncia não encontrado!");
        }
        return denuncia;
    }

    private DenunciaListDTO retornaListDenunciaDTO(Denuncia denuncia) {
        return objectMapper.convertValue(denuncia, DenunciaListDTO.class);
    }

    private DenunciaDTO retornarDTODenuncia(Denuncia entity) {
        return objectMapper.convertValue(entity, DenunciaDTO.class);
    }
}