package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.usuario.CreateUsuarioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UpdateUsuarioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDto;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public UsuarioDto obterUsuario(Integer idUsuario) throws Exception {
        //TODO Será ajustado para mostrar somento o usuario
        Usuario usuario = usuarioRepository.obterUsuario(idUsuario);
        return objectMapper.convertValue(usuario, UsuarioDto.class);
    }

    public UsuarioDto criarUsuario(CreateUsuarioDto novoUsuario) throws Exception {
        Usuario novoUsuarioEntity = objectMapper.convertValue(novoUsuario, Usuario.class);
        Usuario usuarioCriado = usuarioRepository.criarUsuario(novoUsuarioEntity);

        return objectMapper.convertValue(usuarioCriado, UsuarioDto.class);
    }

    public UsuarioDto atualizarUsuario(Integer idUsuario, UpdateUsuarioDto novoUsuario) throws Exception {
        //TODO Será verificado se o usuario editado é o mesmo que o usuario logado
        Usuario novoUsuarioEntity = objectMapper.convertValue(novoUsuario, Usuario.class);
        Usuario usuarioAtualizado = usuarioRepository.atualizarUsuario(idUsuario, novoUsuarioEntity);

        return objectMapper.convertValue(usuarioAtualizado, UsuarioDto.class);
    }

    public void removerUsuario(Integer idUsuario) throws Exception {
        //TODO Será verificado se o usuario deletado é o mesmo que o usuario logado
        usuarioRepository.removerUsuario(idUsuario);
    }
}