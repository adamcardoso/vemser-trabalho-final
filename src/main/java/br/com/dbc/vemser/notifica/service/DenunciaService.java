package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DenunciaService {
    private final DenunciaRepository denunciaRepository;
    private final EmailService emailService;
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public List<DenunciaDTO> listarTodasDenuncias() throws Exception {
        return denunciaRepository.listarTodasDenuncias();
    }
    public List<DenunciaDTO> listByTitulo(String titulo) throws Exception {
        List<Denuncia> denuncias = denunciaRepository.listByTitulo(titulo);

        if (!denuncias.isEmpty()) {
            List<DenunciaDTO> denunciaDTOS = new ArrayList<>();

            for (Denuncia d : denuncias)
                denunciaDTOS.add(objectMapper.convertValue(d, DenunciaDTO.class));

            return denunciaDTOS;
        }

        throw new RegraDeNegocioException("Nenhuma denúncia encontrada com o título fornecido.");
    }

    public List<DenunciaDTO> listByIdUsuario(Integer idUsuario) throws Exception {
        List<Denuncia> denuncias = denunciaRepository.listByIdUsuario(idUsuario);
        if (!denuncias.isEmpty()) {
            List<DenunciaDTO> denunciaDTOS = new ArrayList<>();
            for (Denuncia d : denuncias)
                denunciaDTOS.add(objectMapper.convertValue(d, DenunciaDTO.class));

            return denunciaDTOS;
        }
        throw new RegraDeNegocioException("Nenhuma denúncia encontrada para o usuário com ID fornecido.");
    }

    public DenunciaDTO obterDenunciaById(Integer idDenuncia) throws Exception {
        Denuncia denuncia = denunciaRepository.obterDenunciaById(idDenuncia);

        if (denuncia != null) {
            return objectMapper.convertValue(denuncia, DenunciaDTO.class);
        }

        throw new RegraDeNegocioException("Denúncia não encontrada com o ID fornecido.");
    }

    public DenunciaDTO criarDenuncia(DenunciaCreateDTO denunciaDTO, int idUsuario) throws Exception {
        Denuncia d = objectMapper.convertValue(denunciaDTO, Denuncia.class);
        UsuarioDTO usuario = usuarioService.obterUsuario(idUsuario);
        DenunciaDTO denuncia = objectMapper.convertValue(denunciaRepository.criarDenuncia(d, idUsuario), DenunciaDTO.class);
        emailService.enviarEmailCriacaoDenuncia(usuario.getEmailUsuario(), usuario.getNomeUsuario(), denuncia.getIdDenuncia());
        return denuncia;
    }

    public DenunciaDTO editarDenuncia(DenunciaCreateDTO denunciaCreateDTO, Integer idDenuncia, Integer idUsuario) throws Exception {
        Denuncia denuncia = denunciaRepository.obterDenunciaById(idDenuncia);

        if (denuncia != null) {
            Denuncia d = objectMapper.convertValue(denunciaCreateDTO, Denuncia.class);
            UsuarioDTO usuario = usuarioService.obterUsuario(idUsuario);
            DenunciaDTO denunciaDTO = objectMapper.convertValue(denunciaRepository.editarDenuncia(idDenuncia, d, idUsuario), DenunciaDTO.class);
            emailService.enviarEmailEdicaoEndereco(usuario.getEmailUsuario(), usuario.getNomeUsuario(), denunciaDTO.getIdDenuncia());
            return denunciaDTO;
        }
        throw new RegraDeNegocioException("Denúncia não encontrada com o ID fornecido.");
    }

    public String deletarDenuncia(Integer idDenuncia, Integer idUsuario) throws Exception{
        if (denunciaRepository.deletarDenuncia(idDenuncia, idUsuario)) {
            return "Denúncia Excluída!";
        }
        throw new RegraDeNegocioException("Denúncia não encontrada com o ID fornecido.");
    }
}
