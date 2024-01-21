package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDto;
import br.com.dbc.vemser.notifica.dto.comentario.CreateComentarioDto;
import br.com.dbc.vemser.notifica.dto.comentario.UpdateComentarioDto;
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

    public Optional<ComentarioDto> obterComentarioById(Integer id) throws Exception{
        try{
            Optional<Comentario> comentarioOpt = comentarioRepository.obterComentarioById(id);

            if(comentarioOpt.isPresent()){
                Comentario comentario = comentarioOpt.get();
                ComentarioDto cDto = objectMapper.convertValue(comentario, ComentarioDto.class);

                return Optional.of(cDto);
            }
            return Optional.empty();
        } catch (Exception e){
            throw new Exception();
        }
    }

    public Optional<List<ComentarioDto>> listarComentariosByIdDenuncia(Integer id) throws Exception{
        try{
            Optional<List<Comentario>> comentariosOpt = comentarioRepository.listarComentariosByIdDenuncia(id);

            if(comentariosOpt.isPresent()){
                List<Comentario> comentarios = comentariosOpt.get();
                List<ComentarioDto> comentariosDto = new ArrayList<>();

                for(Comentario c: comentarios)
                    comentariosDto.add(objectMapper.convertValue(c, ComentarioDto.class));

                return Optional.of(comentariosDto);
            }
            return Optional.empty();
        } catch (Exception e){
            throw new Exception();
        }
    }

    public Optional<ComentarioDto> adicionarComentario(CreateComentarioDto comentarioDto) throws Exception{
        try {
            Comentario c = objectMapper.convertValue(comentarioDto, Comentario.class);
            return Optional.of(objectMapper.convertValue(comentarioRepository.adicionarComentario(c), ComentarioDto.class));
        } catch (Exception e){
            throw new Exception();
        }
    }

    public Optional<ComentarioDto> atualizarComentario(Integer id, UpdateComentarioDto comentarioDto) throws Exception{
        try {
            Optional<Comentario> cOpt = comentarioRepository.obterComentarioById(id);

            if(cOpt.isPresent()){
                Comentario c = objectMapper.convertValue(comentarioDto, Comentario.class);
                return Optional.of(objectMapper.convertValue(comentarioRepository.atualizarComentario(id, c), ComentarioDto.class));
            }
            return Optional.empty();
        } catch (Exception e){
            throw new Exception();
        }
    }

    public Optional<Boolean> removerComentario(Integer id) throws Exception{
        try {
            return comentarioRepository.removerComentario(id);
        } catch (Exception e){
            throw new Exception();
        }
    }
}
