package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioPerfilDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.UsuarioAtivo;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.ComentarioRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
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
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    private final TokenService tokenService;
    private final Argon2PasswordEncoder argon2PasswordEncoder;
    private final DenunciaService denunciaService;
    private final ComentarioRepository comentarioRepository;

    public UsuarioPerfilDTO obterUsuarioById(Integer idUsuario) throws RegraDeNegocioException {
       return objectMapper.convertValue(getUsuario(idUsuario), UsuarioPerfilDTO.class);
    }

    public List<ComentarioDTO> listComentario(Integer idUsuario){
        return comentarioRepository.listarComentariosByIdusuario(idUsuario).stream()
                .map(comentario -> objectMapper.convertValue(comentario, ComentarioDTO.class))
                .collect(Collectors.toList());
    }

    public List<DenunciaDTO> listDenuncia(Integer idUsuario) throws RegraDeNegocioException {
        return denunciaService.getDenunciasByIdUsuario(idUsuario).stream()
                .map(denuncia -> objectMapper.convertValue(denuncia, DenunciaDTO.class))
                .collect(Collectors.toList());
    }

    public UsuarioDTO atualizarUsuario(Integer idUsuario, UsuarioUpdateDTO novoUsuario) throws RegraDeNegocioException {
        Usuario usuarioRecuperado = getUsuario(idUsuario);
        usuarioRecuperado.setEmailUsuario(novoUsuario.getEmailUsuario());
        usuarioRecuperado.setEtniaUsuario(novoUsuario.getEtniaUsuario());
        usuarioRecuperado.setGeneroUsuario(novoUsuario.getGeneroUsuario());
        usuarioRecuperado.setNomeUsuario(novoUsuario.getNomeUsuario());
        usuarioRecuperado.setClasseSocial(novoUsuario.getClasseSocial());
        usuarioRecuperado.setDataNascimento(novoUsuario.getDataNascimento());
        usuarioRecuperado.setNumeroCelular(novoUsuario.getNumeroCelular());

        return retornarDTO(usuarioRepository.save(usuarioRecuperado));
    }

    public void removerUsuario(Integer idUsuario) throws RegraDeNegocioException {
        Usuario usuarioDeletado = getUsuario(idUsuario);
        usuarioDeletado.setUsuarioAtivo(UsuarioAtivo.NAO);
        usuarioRepository.save(usuarioDeletado);
    }

    public String attSenha(Integer idUsuario, String senha, String novaSenha) throws RegraDeNegocioException {
            Usuario usuario = getUsuario(idUsuario);
            String senhaIncriptada = argon2PasswordEncoder.encode(senha);
            if (!(usuario.getSenhaUsuario().equals(senhaIncriptada))){
                throw new RegraDeNegocioException("Senha incorreta!");
            }
            usuario.setSenhaUsuario(argon2PasswordEncoder.encode(novaSenha));
            usuarioRepository.save(usuario);
            return tokenService.generateToken(usuario);
    }

    private Usuario getUsuario(Integer id) throws RegraDeNegocioException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Usuario não encontrado!"));
    }

    private UsuarioDTO retornarDTO(Usuario entity) {
        return objectMapper.convertValue(entity, UsuarioDTO.class);
    }
}