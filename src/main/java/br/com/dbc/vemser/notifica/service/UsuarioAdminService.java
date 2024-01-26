package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.usuario.CreateUsuarioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UpdateUsuarioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDto;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.UsuarioAdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioAdminService {
    private final UsuarioAdminRepository usuarioAdminRepository;
    private final ObjectMapper objectMapper;


    public UsuarioDto obterUsuarioById(Integer idUsuario) throws Exception {
        return objectMapper.convertValue(getUsuario(idUsuario), UsuarioDto.class);
    }

    public List<UsuarioDto> listarUsuarios() throws Exception {
        List<Usuario> usuarios = usuarioAdminRepository.listarUsuarios();
        return objectMapper.convertValue(usuarios, objectMapper.getTypeFactory().constructCollectionType(List.class, UsuarioDto.class));
    }

    public UsuarioDto criarUsuarioAdmin(CreateUsuarioDto novoUsuario) throws Exception {
        if(novoUsuario.getTipoUsuario() == TipoUsuario.ADMIN){
            Usuario novoUsuarioEntity = objectMapper.convertValue(novoUsuario, Usuario.class);
            Usuario usuarioCriado = usuarioAdminRepository.criarUsuarioAdmin(novoUsuarioEntity);
            return objectMapper.convertValue(usuarioCriado, UsuarioDto.class);
        }
        throw new RegraDeNegocioException("O usuário não é ADMIN!");
    }

    public UsuarioDto atualizarUsuario(Integer idUsuario, UpdateUsuarioDto novoUsuario) throws Exception {
        //TODO Será verificado se o usuario editado é o mesmo que o usuario logado
        Usuario novoUsuarioEntity = objectMapper.convertValue(novoUsuario, Usuario.class);
        Usuario usuarioAtualizado = usuarioAdminRepository.atualizarUsuario(idUsuario, novoUsuarioEntity);

        return objectMapper.convertValue(usuarioAtualizado, UsuarioDto.class);
    }

    public void removerUsuario(Integer idUsuario) throws Exception {
        usuarioAdminRepository.removerUsuario(getUsuario(idUsuario).getIdUsuario());
    }

    private Usuario getUsuario(Integer id) throws Exception {
        Usuario usuarioRecuperado = usuarioAdminRepository.listarUsuarios().stream()
                .filter(usuario -> usuario.getIdUsuario() == id)
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
        return usuarioRecuperado;
    }
}