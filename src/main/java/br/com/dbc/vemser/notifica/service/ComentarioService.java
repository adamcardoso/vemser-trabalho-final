package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.comentario.CreateComentarioDTO;
import br.com.dbc.vemser.notifica.dto.comentario.UpdateComentarioDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.repository.ComentarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final ObjectMapper objectMapper;

    public Optional<ComentarioDTO> obterComentarioById(Integer id) throws Exception{
        try{
            Optional<Comentario> comentarioOpt = comentarioRepository.obterComentarioById(id);

            if(comentarioOpt.isPresent()){
                Comentario comentario = comentarioOpt.get();
                ComentarioDTO cDto = objectMapper.convertValue(comentario, ComentarioDTO.class);

                return Optional.of(cDto);
            }
            return Optional.empty();
        } catch (Exception e){
            throw new Exception();
        }
    }

    public Optional<List<ComentarioDTO>> listarComentariosByIdDenuncia(Integer id) throws Exception{
        try{
            Optional<List<Comentario>> comentariosOpt = comentarioRepository.listarComentariosByIdDenuncia(id);

            if(comentariosOpt.isPresent()){
                List<Comentario> comentarios = comentariosOpt.get();
                List<ComentarioDTO> comentariosDto = new ArrayList<>();

                for(Comentario c: comentarios)
                    comentariosDto.add(objectMapper.convertValue(c, ComentarioDTO.class));

                return Optional.of(comentariosDto);
            }
            return Optional.empty();
        } catch (Exception e){
            throw new Exception();
        }
    }

    public Optional<ComentarioDTO> criarComentario(CreateComentarioDTO comentarioDto) throws Exception{
        try {
            Comentario c = objectMapper.convertValue(comentarioDto, Comentario.class);
            return Optional.of(objectMapper.convertValue(comentarioRepository.criarComentario(c), ComentarioDTO.class));
        } catch (Exception e){
            throw new Exception();
        }
    }

    public Optional<ComentarioDTO> editarComentario(Integer id, UpdateComentarioDTO comentarioDto) throws Exception{
        try {
            Optional<Comentario> cOpt = comentarioRepository.obterComentarioById(id);

            if(cOpt.isPresent()){
                Comentario c = objectMapper.convertValue(comentarioDto, Comentario.class);
                return Optional.of(objectMapper.convertValue(comentarioRepository.editarComentario(id, c), ComentarioDTO.class));
            }
            return Optional.empty();
        } catch (Exception e){
            throw new Exception();
        }
    }

    public Optional<Boolean> deletarComentario(Integer id) throws Exception{
        try {
            return comentarioRepository.deletarComentario(id);
        } catch (Exception e){
            throw new Exception();
        }
    }
}
