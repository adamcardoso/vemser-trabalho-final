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

    public List<DenunciaDTO> listByIdUsuario(Integer idUsuario) throws Exception {
       return getDenunciasByIdUsuario(idUsuario).stream()
               .map(denuncia -> retornarDTO(denuncia))
               .collect(Collectors.toList());
    }

    public DenunciaDTO criarDenuncia(DenunciaCreateDTO denunciaCreateDTO, Integer idUsuario) throws Exception {
        Usuario usuario = getUsuario(idUsuario);
        Denuncia denuncia = converterCreateDTO(denunciaCreateDTO);
        denuncia.setDataHora(LocalDateTime.now());
        denuncia.setIdUsuario(idUsuario);
        denuncia.setNumeroCurtidas(0);
        denuncia.setUsuario(usuario);


        if (denunciaCreateDTO.getLocalizacao() != null
                && denunciaCreateDTO.getLocalizacao().getLatitude() != null
                && denunciaCreateDTO.getLocalizacao().getLongitude() != null) {

            Localizacao localizacao = new Localizacao();
            localizacao.setLatitude(denunciaCreateDTO.getLocalizacao().getLatitude());
            localizacao.setLongitude(denunciaCreateDTO.getLocalizacao().getLongitude());
            localizacao.setIdDenuncia(denuncia.getIdDenuncia());

            localizacao.setDenuncia(denuncia);
            denuncia.setLocalizacao(localizacao);
        }


        Denuncia savedDenuncia = denunciaRepository.save(denuncia);

        return retornarDTO(savedDenuncia);
    }


    public DenunciaDTO editarDenuncia(DenunciaCreateDTO denunciaCreateDTO, Integer idDenuncia, Integer idUsuario) throws RegraDeNegocioException {
        Usuario usuario = getUsuario(idUsuario);

        Denuncia denuncia = getDenuncia(idDenuncia);

        if (denuncia.getIdUsuario().equals(idUsuario)) {
            denuncia.setIdUsuario(idUsuario);
            denuncia.setUsuario(usuario);

            denuncia.setDescricao(denunciaCreateDTO.getDescricao());
            denuncia.setTitulo(denunciaCreateDTO.getTitulo());
            denuncia.setStatusDenuncia(denunciaCreateDTO.getStatusDenuncia());
            denuncia.setCategoria(denunciaCreateDTO.getCategoria());
            denuncia.setTipoDenuncia(denunciaCreateDTO.getTipoDenuncia());

            return retornarDTO(denunciaRepository.save(denuncia));
        }

        throw new RegraDeNegocioException("Usuário não tem permissão para editar esta denúncia.");
    }

    public void deletarDenuncia(Integer idDenuncia, Integer idUsuario) throws RegraDeNegocioException {
        Denuncia denuncia = getDenuncia(idDenuncia);
        if (denuncia.getIdUsuario().equals(idUsuario)){
            denuncia.setStatusDenuncia(StatusDenuncia.FECHADO);
            denunciaRepository.save(denuncia);
            return;
        }
        throw new RegraDeNegocioException("Não é possivel excluir uma denuncia de outro usuario!");
    }


    public Denuncia converterCreateDTO(DenunciaCreateDTO createDTO) {
        return objectMapper.convertValue(createDTO, Denuncia.class);
    }

    public DenunciaDTO retornarDTO(Denuncia entity) {
        return objectMapper.convertValue(entity, DenunciaDTO.class);
    }

    private Usuario getUsuario(Integer idUsuario){
        return usuarioRepository.findById(idUsuario).get();
    }

    public Denuncia getDenuncia(Integer idDenuncia) throws RegraDeNegocioException {
        Denuncia denuncia = denunciaRepository.getDenunciaAtiva(idDenuncia);

        if (denuncia == null){
            throw new RegraDeNegocioException("Denuncia não encontrada!");
        }
        return denuncia;
    }
    public List<Denuncia> getDenunciasByIdUsuario(Integer idUsuario) throws RegraDeNegocioException {
        List<Denuncia> denuncias = denunciaRepository.getDenunciaAtivaByIdUsuario(idUsuario);

        denuncias.stream()
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Nenhuma denuncia encontrada!"));
        return denuncias;
    }


}
