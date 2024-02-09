package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.LoginRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public LoginService(LoginRepository loginRepository, ObjectMapper objectMapper) {
        this.loginRepository = loginRepository;
        this.objectMapper = objectMapper;
    }

    public UsuarioDTO autenticarUsuario(String email, String senha) throws RegraDeNegocioException {
        Optional<Usuario> usuario = loginRepository.findByEmailUsuarioAndSenhaUsuario(email, senha);

        if (usuario.isPresent()) {
            return objectMapper.convertValue(usuario, UsuarioDTO.class);
        } else {
            throw new RegraDeNegocioException("Credenciais inválidas, Usuário ou Senha Incorretos!");
        }
    }

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
}


