package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Localizacao;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DenunciaService {
    private final DenunciaRepository denunciaRepository;
    private final EmailService emailService;
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public DenunciaDTO obterDenunciaById(Integer idDenuncia) throws Exception {
        Denuncia denuncia = denunciaRepository.findById(idDenuncia)
                .orElseThrow(() -> new RegraDeNegocioException("Denúncia não encontrada com ID: " + idDenuncia));
        return objectMapper.convertValue(denuncia, DenunciaDTO.class);
    }
    public Usuario ObterUsuarioById(Integer idUsuario) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado com ID: " + idUsuario));
        return usuario;
    }

    public List<DenunciaDTO> listByIdUsuario(Integer idUsuario) throws Exception {
        List<Denuncia> denuncias = denunciaRepository.findAllByUsuario_IdUsuario(idUsuario);
        if (denuncias.isEmpty()) {
            throw new RegraDeNegocioException("Nenhuma denúncia encontrada para o usuário com ID: " + idUsuario);
        }
        return denuncias.stream()
                .map(this::retornarDTO)
                .collect(Collectors.toList());
    }

    public DenunciaDTO criarDenuncia(DenunciaCreateDTO denunciaCreateDTO, Integer idUsuario) throws Exception {
        Usuario usuario = ObterUsuarioById(idUsuario);

        Denuncia denuncia = converterCreateDTO(denunciaCreateDTO, usuario);
        denuncia.setDataHora(LocalDateTime.now());
        denuncia.setIdUsuario(idUsuario);
        denuncia.setNumeroCurtidas(0);

        Denuncia d = denunciaRepository.save(denuncia);

        if (denunciaCreateDTO.getLocalizacao() != null
                && denunciaCreateDTO.getLocalizacao().getLatitude() != null
                && denunciaCreateDTO.getLocalizacao().getLongitude() != null) {

            Localizacao localizacao = new Localizacao();
            localizacao.setLatitude(denunciaCreateDTO.getLocalizacao().getLatitude());
            localizacao.setLongitude(denunciaCreateDTO.getLocalizacao().getLongitude());
            localizacao.setIdDenuncia(d.getIdDenuncia());

            localizacao.setDenuncia(d);
            d.setLocalizacao(localizacao);
        }

        Denuncia savedDenuncia = denunciaRepository.save(d);

        return retornarDTO(savedDenuncia);
    }


    public DenunciaDTO editarDenuncia(DenunciaCreateDTO denunciaCreateDTO, Integer idDenuncia, Integer idUsuario) throws Exception {
        Usuario usuario = ObterUsuarioById(idUsuario);

        Denuncia denuncia = objectMapper.convertValue(obterDenunciaById(idDenuncia), Denuncia.class);

        if ((denuncia.getIdUsuario() != idUsuario && denuncia.getIdUsuario() != null) ||
                (denuncia.getUsuario() != null && denuncia.getUsuario().equals(usuario))) {
            throw new RegraDeNegocioException("Usuário não tem permissão para editar esta denúncia.");
        }

        denuncia.setIdUsuario(idUsuario);
        denuncia.setUsuario(usuario);

        denuncia.setDescricao(denunciaCreateDTO.getDescricao());
        denuncia.setTitulo(denunciaCreateDTO.getTitulo());
        denuncia.setStatusDenuncia(denunciaCreateDTO.getStatusDenuncia());
        denuncia.setCategoria(denunciaCreateDTO.getCategoria());
        denuncia.setTipoDenuncia(denunciaCreateDTO.getTipoDenuncia());

        return retornarDTO(denunciaRepository.save(denuncia));
    }

    public void deletarDenuncia(Integer idDenuncia, Integer idUsuario) throws Exception {
        Denuncia denuncia = objectMapper.convertValue(obterDenunciaById(idDenuncia), Denuncia.class);
        denuncia.setStatusDenuncia(StatusDenuncia.FECHADO);
    }

    public Denuncia converterDTO(DenunciaDTO dto) {
        return objectMapper.convertValue(dto, Denuncia.class);
    }

    public Denuncia converterCreateDTO(DenunciaCreateDTO createDTO, Usuario usuario) {
        Denuncia denuncia = objectMapper.convertValue(createDTO, Denuncia.class);
        denuncia.setUsuario(usuario);
        return denuncia;
    }

    public DenunciaDTO retornarDTO(Denuncia entity) {
        DenunciaDTO dto = objectMapper.convertValue(entity, DenunciaDTO.class);

        if (entity.getTipoDenuncia().getIdTipoDenuncia() == 0) {
            dto.setUsuario(objectMapper.convertValue(entity.getUsuario(), UsuarioDTO.class));
        }

        if (entity.getLocalizacao() != null) {
            LocalizacaoDTO localizacaoDTO = objectMapper.convertValue(entity.getLocalizacao(), LocalizacaoDTO.class);
            dto.setLocalizacao(localizacaoDTO);
        }

        return dto;
    }


}
