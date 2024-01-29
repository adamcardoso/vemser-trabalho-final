package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public UsuarioDTO obterUsuarioById(Integer idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.obterUsuarioById(idUsuario);
        if(usuario.getEmailUsuario()!=null){
            return objectMapper.convertValue(usuario, UsuarioDTO.class);
        }else {
            throw new RegraDeNegocioException("Usuário não encontrado");
        }

    }

    public UsuarioDTO criarUsuario(UsuarioCreateDTO novoUsuario) throws Exception {
        Usuario novoUsuarioEntity = objectMapper.convertValue(novoUsuario, Usuario.class);
        Usuario usuarioCriado = usuarioRepository.criarUsuario(novoUsuarioEntity);

        return objectMapper.convertValue(usuarioCriado, UsuarioDTO.class);
    }

    public UsuarioDTO atualizarUsuario(Integer idUsuario, UsuarioUpdateDTO novoUsuario) throws Exception {
        //TODO Será verificado se o usuario editado é o mesmo que o usuario logado
        Usuario novoUsuarioEntity = objectMapper.convertValue(novoUsuario, Usuario.class);
        Usuario usuarioAtualizado = usuarioRepository.atualizarUsuario(idUsuario, novoUsuarioEntity);

        return objectMapper.convertValue(usuarioAtualizado, UsuarioDTO.class);
    }

    public void removerUsuario(Integer idUsuario) throws Exception {
        //TODO Será verificado se o usuario deletado é o mesmo que o usuario logado
        usuarioRepository.removerUsuario(idUsuario);
    }
}