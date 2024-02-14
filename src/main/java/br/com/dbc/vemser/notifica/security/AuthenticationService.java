package br.com.dbc.vemser.notifica.security;

import br.com.dbc.vemser.notifica.entity.Instituicao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioEntityOptional = loginService.findByEmailUsuario(username);
        if (usuarioEntityOptional.isPresent()) {
            return usuarioEntityOptional.get();
        }

        Optional<Instituicao> instituicaoEntityOptional = loginService.findByEmailInstituicao(username);
        if (instituicaoEntityOptional.isPresent()) {
            return instituicaoEntityOptional.get();
        }

        throw new UsernameNotFoundException("Usuário ou Instituição inválidos");
    }
}
