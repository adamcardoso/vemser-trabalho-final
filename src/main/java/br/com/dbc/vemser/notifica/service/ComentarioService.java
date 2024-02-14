package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioUpdateDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.ComentarioRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final DenunciaRepository denunciaRepository;
    private final ObjectMapper objectMapper;


    public ComentarioDTO criarComentario(Integer idUsuario, Integer idDenuncia, String comentario) throws RegraDeNegocioException {
        Denuncia denuncia = getDenuncia(idDenuncia);
        Usuario usuario = getUsuario(idUsuario);
        Comentario comentarioCriado = new Comentario();
        comentarioCriado.setComentario(comentario);
        comentarioCriado.setNumeroCurtidas(0);
        comentarioCriado.setDenuncia(denuncia);
        comentarioCriado.setIdDenuncia(idDenuncia);
        comentarioCriado.setUsuario(usuario);
        comentarioCriado.setIdUsuario(idUsuario);
        comentarioCriado = comentarioRepository.save(comentarioCriado);
        return retornaComentarioDTO(comentarioCriado);
    }

    public ComentarioDTO editarComentario(Integer idUsuario, Integer idComentario,
                                          String comentarioAtualizado) throws RegraDeNegocioException {
        Comentario comentario = getComentario(idComentario);
        log.info(comentario.getIdUsuario().toString());
        log.info(idUsuario.toString());
        if(comentario.getIdUsuario().equals(idUsuario)){
            comentario.setComentario(comentarioAtualizado);
            return retornaComentarioDTO(comentarioRepository.save(comentario));
        }

        throw new RegraDeNegocioException("você so pode editar seus comentarios!");

    }

    public void deletarComentario(Integer idUsuario, Integer idComentario) throws RegraDeNegocioException{
        Comentario comentario = getComentario(idComentario);
        log.info(comentario.getIdUsuario().toString());
        log.info(idUsuario.toString());
        if(comentario.getIdUsuario().equals(idUsuario)){
            comentarioRepository.delete(comentario);
            return;
        }

        throw new RegraDeNegocioException("você so pode excluir seus comentarios!");
    }

    private Comentario getComentario(Integer idComentario) throws RegraDeNegocioException {
        Optional<Comentario> optionalComentario = comentarioRepository.findById(idComentario);
        if (optionalComentario.isEmpty()) {
            throw new RegraDeNegocioException("Comentário não encontrado!");
        }
        return optionalComentario.get();
    }

    private Denuncia getDenuncia(Integer idDenuncia) throws RegraDeNegocioException {
        Denuncia denuncia = denunciaRepository.getDenunciaAtiva(idDenuncia);
        if (denuncia == null){
            throw new RegraDeNegocioException("Denuncia não encontrada!");
        }
        return denuncia;
    }

    private Usuario getUsuario(Integer idUsuario) throws RegraDeNegocioException {
        return usuarioRepository.getById(idUsuario);

    }

    private ComentarioDTO retornaComentarioDTO(Comentario comentario){
        return objectMapper.convertValue(comentario, ComentarioDTO.class);
    }
}
