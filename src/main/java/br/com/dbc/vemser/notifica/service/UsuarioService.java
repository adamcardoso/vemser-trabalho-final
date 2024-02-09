package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.UsuarioAtivo;
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
       return retornarDTO(getUsuario(idUsuario));
    }

    public UsuarioDTO atualizarUsuario(Integer idUsuario, UsuarioUpdateDTO novoUsuario) throws Exception {
        Usuario usuarioRecuperado = getUsuario(idUsuario);
        usuarioRecuperado.setEmailUsuario(novoUsuario.getEmailUsuario());
        usuarioRecuperado.setEtniaUsuario(novoUsuario.getEtniaUsuario());
        usuarioRecuperado.setGeneroUsuario(novoUsuario.getGeneroUsuario());
        usuarioRecuperado.setNomeUsuario(novoUsuario.getNomeUsuario());
        usuarioRecuperado.setSenhaUsuario(novoUsuario.getSenhaUsuario());
        usuarioRecuperado.setTipoUsuario(novoUsuario.getTipoUsuario());
        usuarioRecuperado.setClasseSocial(novoUsuario.getClasseSocial());
        usuarioRecuperado.setDataNascimento(novoUsuario.getDataNascimento());
        usuarioRecuperado.setNumeroCelular(novoUsuario.getNumeroCelular());

        return retornarDTO(usuarioRepository.save(usuarioRecuperado));
    }

    public void removerUsuario(Integer idUsuario) throws Exception {
        Usuario usuarioDeletado = getUsuario(idUsuario);
        usuarioDeletado.setUsuarioAtivo(UsuarioAtivo.SIM);
    }

    private Usuario getUsuario(Integer id) throws RegraDeNegocioException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Usuario não encontrado!"));
    }

    private Usuario converterDTO(UsuarioCreateDTO dto) {
        return objectMapper.convertValue(dto, Usuario.class);
    }

    private UsuarioDTO retornarDTO(Usuario entity) {
        return objectMapper.convertValue(entity, UsuarioDTO.class);
    }
}