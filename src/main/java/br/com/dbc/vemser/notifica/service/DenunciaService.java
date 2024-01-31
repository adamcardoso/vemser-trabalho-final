package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DenunciaService {
    private final DenunciaRepository denunciaRepository;
    private final EmailService emailService;
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public List<DenunciaDTO> listarTodasDenuncias() throws Exception {
        return denunciaRepository.findAll().stream()
                .map(this::retornarDTO)
                .collect(Collectors.toList());
    }

    public Optional<Denuncia> obterDenunciaById(Integer idDenuncia) throws Exception {
        return denunciaRepository.findById(idDenuncia);
    }

    public List<DenunciaDTO> listByIdUsuario(Integer idUsuario) throws Exception {
        return denunciaRepository.findAllByUsuario_IdUsuario(idUsuario).stream()
                .map(this::retornarDTO)
                .collect(Collectors.toList());
    }

    public DenunciaDTO criarDenuncia(DenunciaCreateDTO denunciaCreateDTO, Integer idUsuario) throws Exception {
        UsuarioDTO usuario = objectMapper.convertValue(usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado com ID: " + idUsuario)), UsuarioDTO.class);

        Denuncia denuncia = converterCreateDTO(denunciaCreateDTO);
        denuncia.setUsuario(objectMapper.convertValue(usuario, Usuario.class));
        denuncia.setDataHora(LocalDateTime.now());

        return retornarDTO(denunciaRepository.save(denuncia));
    }

    public DenunciaDTO editarDenuncia(DenunciaCreateDTO denunciaCreateDTO, Integer idDenuncia, Integer idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado com ID: " + idUsuario));

        Denuncia denuncia = denunciaRepository.findById(idDenuncia)
                .orElseThrow(() -> new RegraDeNegocioException("Denúncia não encontrada com ID: " + idDenuncia));

        if (!denuncia.getUsuario().equals(usuario)) {
            throw new RegraDeNegocioException("Usuário não tem permissão para editar esta denúncia.");
        }

        denuncia.setDescricao(denunciaCreateDTO.getDescricao());
        denuncia.setTitulo(denunciaCreateDTO.getTitulo());
        denuncia.setStatusDenuncia(denunciaCreateDTO.getStatusDenuncia());
        denuncia.setCategoria(denunciaCreateDTO.getCategoria());
        denuncia.setTipoDenuncia(denunciaCreateDTO.getTipoDenuncia());

        return retornarDTO(denunciaRepository.save(denuncia));
    }

    public String deletarDenuncia(Integer idDenuncia, Integer idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado com ID: " + idUsuario));

        Denuncia denuncia = denunciaRepository.findById(idDenuncia)
                .orElseThrow(() -> new RegraDeNegocioException("Denúncia não encontrada com ID: " + idDenuncia));

        if (!denuncia.getUsuario().equals(usuario)) {
            throw new RegraDeNegocioException("Usuário não tem permissão para excluir esta denúncia.");
        }

        denunciaRepository.deleteById(idDenuncia);

        return "Denúncia excluída com sucesso.";
    }

    public Denuncia converterDTO(DenunciaDTO dto) {
        return objectMapper.convertValue(dto, Denuncia.class);
    }
    public DenunciaDTO retornarDTO(Denuncia entity) {
        return objectMapper.convertValue(entity, DenunciaDTO.class);
    }
    public Denuncia converterCreateDTO(DenunciaCreateDTO createDTO) {return objectMapper.convertValue(createDTO, Denuncia.class);}


}
