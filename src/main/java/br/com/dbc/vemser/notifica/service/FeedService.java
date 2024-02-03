package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class FeedService {
    private final DenunciaRepository denunciaRepository;
    private final ObjectMapper objectMapper;
    public List<DenunciaDTO> listarTodasDenuncias() throws Exception {
        List<Denuncia> denuncias = denunciaRepository.findAll();
        if (denuncias.isEmpty()) {
            throw new RegraDeNegocioException("Nenhuma denúncia encontrada.");
        }
        return denuncias.stream()
                .map(this::retornarDTO)
                .collect(Collectors.toList());
    }
    public DenunciaDTO retornarDTO(Denuncia entity) {
        return objectMapper.convertValue(entity, DenunciaDTO.class);
    }

    public List<DenunciaDTO> listarTodasDenunciasComComentarios() throws RegraDeNegocioException {
        List<DenunciaDTO> denunciaDTOs = new ArrayList<>();
        List<Denuncia> denuncias = denunciaRepository.findAll();

        for (Denuncia denuncia : denuncias) {
            denunciaDTOs.add(retornarComentariosDTOs(denuncia));
        }

        return denunciaDTOs;
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
//        comentarioDTO.setCurtida(comentario.getCurtida());
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
