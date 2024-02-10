package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.UsuarioAtivo;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
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

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final Argon2PasswordEncoder argon2PasswordEncoder;

    public UsuarioDTO obterUsuarioById(Integer idUsuario) throws Exception {
       return retornarDTO(getUsuario(idUsuario));
    }

    public UsuarioDTO atualizarUsuario(Integer idUsuario, UsuarioUpdateDTO novoUsuario) throws Exception {
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

    public void removerUsuario(Integer idUsuario) throws Exception {
        Usuario usuarioDeletado = getUsuario(idUsuario);
        usuarioDeletado.setUsuarioAtivo(UsuarioAtivo.NAO);
        usuarioRepository.save(usuarioDeletado);
    }

    public String attSenha(Integer idUsuario, String senha, String novaSenha) throws RegraDeNegocioException {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            getUsuario(idUsuario).getEmailUsuario(),
                            senha
                    );

            Authentication authentication =
                    authenticationManager.authenticate(
                            usernamePasswordAuthenticationToken);

            Usuario usuarioValidado = (Usuario) authentication.getPrincipal();
            usuarioValidado.setSenhaUsuario(argon2PasswordEncoder.encode(novaSenha));
            usuarioRepository.save(usuarioValidado);
            return tokenService.generateToken(usuarioValidado);
        } catch (AuthenticationException ex) {
            throw new RegraDeNegocioException("Senha incorreta!");
        }
    }

    private Usuario getUsuario(Integer id) throws RegraDeNegocioException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Usuario não encontrado!"));
    }

    private UsuarioDTO retornarDTO(Usuario entity) {
        return objectMapper.convertValue(entity, UsuarioDTO.class);
    }
}