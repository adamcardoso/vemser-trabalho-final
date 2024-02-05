package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.feed.FeedDenunciasDto;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.IFeedRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final DenunciaRepository denunciaRepository;
    private final IFeedRepository feedRepository;
    private final ObjectMapper objectMapper;
    public Page<FeedDenunciasDto> listarTodasDenuncias(Pageable pageable) throws Exception {
        return denunciaRepository.findAll(pageable).map(d ->
                new FeedDenunciasDto(d.getIdDenuncia(), d.getDescricao(), d.getTitulo(), d.getDataHora(),
                        d.getStatusDenuncia(), d.getCategoria(), d.getTipoDenuncia(), d.getCurtidas().size(), d.getUsuario(), d.getLocalizacao()));
    }
    public DenunciaDTO retornarDTO(Denuncia entity) {
        return objectMapper.convertValue(entity, DenunciaDTO.class);
    }

    public Page<FeedDenunciasDto> listarTodasDenunciasComComentarios(Pageable pageable) throws RegraDeNegocioException {
        return denunciaRepository.findAll(pageable).map(d ->
                new FeedDenunciasDto(d.getIdDenuncia(), d.getDescricao(), d.getTitulo(), d.getDataHora(),
                        d.getStatusDenuncia(), d.getCategoria(), d.getTipoDenuncia(), d.getCurtidas().size(), d.getUsuario(),
                        d.getComentarios(), d.getLocalizacao()));
    }

    private DenunciaDTO retornarComentariosDTOs(Denuncia denuncia) {
        DenunciaDTO denunciaDTO = retornarDTO(denuncia);

        List<ComentarioDTO> comentarioDTOSet = new ArrayList<>();
        for (Comentario comentario : denuncia.getComentarios()) {
            comentarioDTOSet.add(retornarComentarioDTO(comentario));
        }

        denunciaDTO.setComentarios(comentarioDTOSet);

        return denunciaDTO;
    }

    private ComentarioDTO retornarComentarioDTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setIdComentario(comentario.getIdComentario());
        comentarioDTO.setComentario(comentario.getComentario());
        comentarioDTO.setUsuario(objectMapper.convertValue(comentario.getUsuario(), UsuarioDTO.class));
        comentarioDTO.setDenuncia(objectMapper.convertValue(comentario.getDenuncia(), DenunciaDTO.class));

        return comentarioDTO;
    }


    public DenunciaDTO listarDenunciaComComentarios(Integer idDenuncia) throws RegraDeNegocioException {
        Optional<Denuncia> denunciaOptional = denunciaRepository.findById(idDenuncia);

        if (denunciaOptional.isPresent()) {
            Denuncia denuncia = denunciaOptional.get();

            List<ComentarioDTO> comentarioDTOList = new ArrayList<>();
            for (Comentario comentario : denuncia.getComentarios()) {
                comentarioDTOList.add(retornarComentarioDTO(comentario));
            }

            DenunciaDTO denunciaDTO = retornarDTO(denuncia);
            denunciaDTO.setComentarios(comentarioDTOList);

            return denunciaDTO;
        } else {
            throw new RegraDeNegocioException("Denúncia não Encontrada!");
        }
    }

}
