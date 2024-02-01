package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
