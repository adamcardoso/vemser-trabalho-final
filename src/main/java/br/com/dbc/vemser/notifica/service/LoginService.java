package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Instituicao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import br.com.dbc.vemser.notifica.entity.enums.UsuarioAtivo;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.InstituicaoRepository;
import br.com.dbc.vemser.notifica.repository.LoginRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final ObjectMapper objectMapper;
    private final UsuarioRepository usuarioRepository;
    private final Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();
    private final InstituicaoRepository instituicaoRepository;
    private final CreateRegistrosService createRegistrosService;

    public UsuarioDTO createUsuario(UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        Usuario usuarioDesativado = usuarioRepository.usuarioInativoCadastrado(usuarioCreateDTO.getNumeroCelular(),
                usuarioCreateDTO.getEmailUsuario());
        if(usuarioDesativado != null){
            usuarioDesativado.setUsuarioAtivo(UsuarioAtivo.SIM);
            return objectMapper.convertValue(usuarioRepository.save(usuarioDesativado),UsuarioDTO.class);
        }
        if (loginRepository.findByEmailUsuario(usuarioCreateDTO.getEmailUsuario()).isPresent()) {
            throw new RegraDeNegocioException("Credenciais inválidas, Usuário Já Cadastrado!");
        }
        if (loginRepository.findByNumeroCelular(usuarioCreateDTO.getNumeroCelular()).isPresent()) {
            throw new RegraDeNegocioException("Esse Número Já Está Cadastrado!");
        }
        Usuario usuarioCriado = objectMapper.convertValue(usuarioCreateDTO, Usuario.class);
        usuarioCriado.setSenhaUsuario(argon2PasswordEncoder.encode(usuarioCreateDTO.getSenhaUsuario()));
        usuarioCriado.setTipoUsuario(TipoUsuario.COMUM);
        usuarioCriado.setUsuarioAtivo(UsuarioAtivo.SIM);
        usuarioRepository.save(usuarioCriado);

        createRegistrosService.insertCreateRegistro();
        return objectMapper.convertValue(usuarioCriado,UsuarioDTO.class);
    }

    public Optional<Usuario> findById(Integer idUsuario) {
        return loginRepository.findByIdUsuario(idUsuario);
    }
    public Optional<Instituicao> findByIdIntituicao(Integer idInstituicao) {
        return instituicaoRepository.findByIdInstituicao(idInstituicao);
    }

    public Optional<Usuario> findByEmailUsuario(String username) {
        return loginRepository.findByEmailUsuario(username);
    }

    public Integer getIdLoggedUser() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

    public Optional<Instituicao> findByEmailInstituicao(String emailInstituicao) {
        return instituicaoRepository.findByEmailInstituicao(emailInstituicao);
    }

    public Instituicao getLoggedInstituicao() throws RegraDeNegocioException {
        return findByIdIntituicao(getIdLoggedUser()).orElseThrow(() -> new RegraDeNegocioException("Instituição não encontrado."));
    }
}


