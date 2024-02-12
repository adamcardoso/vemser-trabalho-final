package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import br.com.dbc.vemser.notifica.entity.enums.UsuarioAtivo;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.LoginRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final ObjectMapper objectMapper;
//    private final Argon2PasswordEncoder argon2PasswordEncoder;
    public final UsuarioRepository usuarioRepository;

    public UsuarioDTO autenticarUsuario(String email, String senha) throws RegraDeNegocioException {
        Optional<Usuario> usuario = loginRepository.findByEmailUsuarioAndSenhaUsuario(email, senha);

        if (usuario.isPresent()) {
            return objectMapper.convertValue(usuario, UsuarioDTO.class);
        } else {
            return null;
        }
    }

//    public UsuarioDTO createUsuario(UsuarioCreateDTO usuarioCreateDTO ) throws RegraDeNegocioException {
//        if (findByEmailUsuario(usuarioCreateDTO.getEmailUsuario()).isPresent()) {
//            throw new RegraDeNegocioException("Credenciais inválidas, Usuário Já Cadastrado!");
//        }
//
//        String senhaCriptografada = argon2PasswordEncoder.encode(usuarioCreateDTO.getSenhaUsuario());
//
//        Usuario usuarioEntity = new Usuario();
//        usuarioEntity.setNomeUsuario(usuarioCreateDTO.getNomeUsuario());
//        usuarioEntity.setEmailUsuario(usuarioCreateDTO.getEmailUsuario());
//        if (findByNumeroCelular(usuarioCreateDTO.getNumeroCelular())!=null) {
//            throw new RegraDeNegocioException("Esse Número Já Está Cadastrado!");
//        }
//        usuarioEntity.setNumeroCelular(usuarioCreateDTO.getNumeroCelular());
//        usuarioEntity.setSenhaUsuario(senhaCriptografada);
//        usuarioEntity.setEtniaUsuario(usuarioCreateDTO.getEtniaUsuario());
//        usuarioEntity.setDataNascimento(usuarioCreateDTO.getDataNascimento());
//        usuarioEntity.setClasseSocial(usuarioCreateDTO.getClasseSocial());
//        usuarioEntity.setGeneroUsuario(usuarioCreateDTO.getGeneroUsuario());
//        usuarioEntity.setTipoUsuario(TipoUsuario.COMUM);
//        usuarioEntity.setUsuarioAtivo(UsuarioAtivo.SIM);
//
//        return objectMapper.convertValue(usuarioRepository.save(usuarioEntity),UsuarioDTO.class);
//    }

    public Optional<Usuario> findById(Integer idUsuario) {
        return loginRepository.findByIdUsuario(idUsuario);
    }

    public Optional<Usuario> findByEmailUsuario(String username) {
        return loginRepository.findByEmailUsuario(username);
    }

    public Integer getIdLoggedUser() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

    public Usuario getLoggedUser() throws RegraDeNegocioException {
        return findById(getIdLoggedUser()).orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));
    }

    public UsuarioDTO findByNumeroCelular(String numeroCelular) throws RegraDeNegocioException {
        Optional<Usuario> usuario = loginRepository.findByNumeroCelular(numeroCelular);

        if (usuario.isPresent()) {
            return objectMapper.convertValue(usuario, UsuarioDTO.class);
        } else {
            return null;
        }
    }
}


