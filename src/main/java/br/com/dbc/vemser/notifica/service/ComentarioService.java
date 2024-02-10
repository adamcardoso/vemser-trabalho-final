package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioUpdateDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.ComentarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final DenunciaRepository denunciaRepository;
    private final ObjectMapper objectMapper;


    public ComentarioDTO criarComentario(Integer idUsuario, Integer idDenuncia, String comentario) throws RegraDeNegocioException {
        Denuncia denuncia = getDenuncia(idDenuncia);
        Comentario comentarioCriado = new Comentario(idDenuncia,idUsuario,comentario,0);
        comentarioCriado = comentarioRepository.save(comentarioCriado);
        denuncia.getComentarios().add(comentarioCriado);
        return retornaComentarioDTO(comentarioCriado);
    }

    public ComentarioDTO editarComentario(Integer idUsuario, Integer idComentario,
                                          String comentarioAtualizado) throws RegraDeNegocioException {
        Comentario comentario = getComentario(idComentario);
        if(comentario.getIdUsuario().equals(idComentario)){
            comentario.setComentario(comentarioAtualizado);
            return retornaComentarioDTO(comentarioRepository.save(comentario));
        }

        throw new RegraDeNegocioException("você so pode editar seus comentarios!");

    }

    public void deletarComentario(Integer idUsuario, Integer idComentario) throws RegraDeNegocioException{
        Comentario comentario = getComentario(idComentario);
        if(comentario.getIdUsuario().equals(idComentario)){
            comentarioRepository.delete(comentario);
        }

        throw new RegraDeNegocioException("você so pode excluir seus comentarios!");
    }

    private Comentario getComentario(Integer idComentario) throws RegraDeNegocioException {
        Comentario comentario = comentarioRepository.findById(idComentario).get();
        if (comentario == null){
            throw new RegraDeNegocioException("Comentario não encontrado!");
        }
        return comentario;
    }

    private Denuncia getDenuncia(Integer idDenuncia) throws RegraDeNegocioException {
        Denuncia denuncia = denunciaRepository.getDenunciaAtiva(idDenuncia);
        if (denuncia == null){
            throw new RegraDeNegocioException("Denuncia não encontrada!");
        }
        return denuncia;
    }

    private ComentarioDTO retornaComentarioDTO(Comentario comentario){
        return objectMapper.convertValue(comentario, ComentarioDTO.class);
    }
}
