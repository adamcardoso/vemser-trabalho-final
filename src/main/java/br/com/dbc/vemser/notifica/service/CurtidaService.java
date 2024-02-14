package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Curtida;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.ComentarioRepository;
import br.com.dbc.vemser.notifica.repository.CurtidaRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CurtidaService{
    private final CurtidaRepository curtidaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioRepository comentarioRepository;
    private final DenunciaRepository denunciaRepository;


    public String apoiarDenuncia(Integer idUsuario, Integer idDenuncia) throws Exception {
        Denuncia denuncia = getDenuncia(idDenuncia);
        if(curtidaRepository.findByIdUsuarioAndDenuncia(idUsuario, idDenuncia).isPresent()){
           Curtida curtidaExistente = curtidaRepository.findByIdUsuarioAndDenuncia(idUsuario, idDenuncia).get();
           curtidaRepository.delete(curtidaExistente);
            denuncia.setNumeroCurtidas(curtidaRepository.numereCurtidasByDenuncia(idDenuncia));
            denunciaRepository.save(denuncia);
            return "dislike";
       }
       Curtida curtida = new Curtida(usuarioRepository.findById(idUsuario).get(), denuncia, LocalDateTime.now());
        curtidaRepository.save(curtida);
        denuncia.setNumeroCurtidas(curtidaRepository.numereCurtidasByDenuncia(idDenuncia));
        denunciaRepository.save(denuncia);
        return "Like";
    }

    public String apoiarComentario(Integer idUsuario, Integer idComentario) throws Exception {
        Comentario comentario = getComentario(idComentario);
        if(curtidaRepository.findByIdUsuarioAndIdComentario(idUsuario, idComentario).isPresent()){
            Curtida curtidaExistente = curtidaRepository.findByIdUsuarioAndIdComentario(idUsuario, idComentario).get();
            curtidaRepository.delete(curtidaExistente);
            comentario.setNumeroCurtidas(curtidaRepository.numereCurtidasByComentario(idComentario));
            comentarioRepository.save(comentario);
            return "dislike";
        }
        Curtida curtida = new Curtida(usuarioRepository.findById(idUsuario).get(), comentario, LocalDateTime.now());
        curtidaRepository.save(curtida);
        comentario.setNumeroCurtidas(curtidaRepository.numereCurtidasByComentario(idComentario));
        comentarioRepository.save(comentario);
        return "Like";
    }

    public Comentario getComentario(Integer idComentario) throws RegraDeNegocioException {
        Optional<Comentario> comentario = comentarioRepository.findById(idComentario);
        if (comentario.isEmpty()){
            throw new RegraDeNegocioException("Comentario não encontrado!");
        }
        return comentario.get();
    }

    public Denuncia getDenuncia(Integer idDenuncia) throws RegraDeNegocioException {
        Denuncia denuncia = denunciaRepository.getDenunciaAtiva(idDenuncia);
        if (denuncia == null){
            throw new RegraDeNegocioException("Denuncia não encontrada!");
        }
        return denuncia;
    }
}
