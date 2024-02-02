package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioUpdateDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.IComentarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ComentarioService {
    private final IComentarioRepository comentarioRepository;
    private final DenunciaRepository denunciaRepository;
    private final ObjectMapper objectMapper;

    public ComentarioDTO obterComentarioById(Integer id) throws Exception{
        return comentarioRepository.findById(id)
                .map(this::returnComentarioDto)
                .orElseThrow(() -> new RegraDeNegocioException("Comentário não encontrado"));
    }

    public List<ComentarioDTO> listarComentariosByIdDenuncia(Integer id) throws Exception{
        return comentarioRepository.listarComentariosByIdDenuncia(id)
                .stream()
                .map(this::returnComentarioDto)
                .collect(Collectors.toList());
    }

    public ComentarioDTO criarComentario(ComentarioCreateDTO comentarioDto) throws Exception{
        Denuncia d = denunciaRepository.getById(comentarioDto.getIdDenuncia());
        Comentario c = objectMapper.convertValue(comentarioDto, Comentario.class);
        c.setDenuncia(d);

        ComentarioDTO cDto =objectMapper.convertValue(comentarioRepository.save(c), ComentarioDTO.class);
        cDto.setIdDenuncia(d.getIdDenuncia());
        return cDto;
    }

    public ComentarioDTO editarComentario(Integer id, ComentarioUpdateDTO comentarioDto) throws Exception{
        comentarioRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("Comentário não encontrado!"));

        Denuncia d = denunciaRepository.getById(comentarioDto.getIdDenuncia());
        Comentario c = objectMapper.convertValue(comentarioDto, Comentario.class);
        c.setIdComentario(id);
        c.setDenuncia(d);

        ComentarioDTO cDto = returnComentarioDto(comentarioRepository.save(c));
        cDto.setIdDenuncia(d.getIdDenuncia());
        return cDto;
    }

    public void deletarComentario(Integer id) throws Exception{
        comentarioRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("Comentário não encontrado!"));
        comentarioRepository.deleteById(id);
    }

    private ComentarioDTO returnComentarioDto(Comentario c){
        return new ComentarioDTO(c.getIdComentario(), c.getComentario(), c.getCurtida(), c.getDenuncia().getIdDenuncia(), c.getUsuario().getIdUsuario());
    }
}
