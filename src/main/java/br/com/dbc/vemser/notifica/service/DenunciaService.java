package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDto;
import br.com.dbc.vemser.notifica.dto.comentario.UpdateComentarioDto;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DenunciaService {
    private final DenunciaRepository denunciaRepository;
    private final ObjectMapper objectMapper;

    public Optional<List<DenunciaDTO>> listarTodasDenuncias() throws Exception {
        try {
            Optional<List<DenunciaDTO>> denunciaDTOS = denunciaRepository.listarTodasDenuncias();
            return denunciaDTOS;
        } catch (Exception e) {
            throw new Exception("Erro ao listar as denúncias", e);
        }
    }

    public Optional<List<DenunciaDTO>> listByTitulo(String titulo) throws Exception {
        try{
            Optional<List<Denuncia>> denunciasopt = denunciaRepository.listByTitulo(titulo);

            if(denunciasopt.isPresent()){
                List<Denuncia> denuncias = denunciasopt.get();
                List<DenunciaDTO> denunciaDTOS = new ArrayList<>();

                for(Denuncia d: denuncias)
                    denunciaDTOS.add(objectMapper.convertValue(d, DenunciaDTO.class));

                return Optional.of(denunciaDTOS);
            }
            return Optional.empty();
        } catch (Exception e){
            throw new Exception();
        }
    }

    public Optional<List<DenunciaDTO>> listByIdUsuario(Integer idUsuario) throws Exception {
        try {
            Optional<List<Denuncia>> denunciasOpt = denunciaRepository.ListByIdUsuario(idUsuario);

            if (denunciasOpt.isPresent()) {
                List<Denuncia> denuncias = denunciasOpt.get();
                List<DenunciaDTO> denunciaDTOS = new ArrayList<>();

                for (Denuncia d : denuncias)
                    denunciaDTOS.add(objectMapper.convertValue(d, DenunciaDTO.class));

                return Optional.of(denunciaDTOS);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new Exception("Erro ao listar as denúncias do usuário.", e);
        }
    }

    public Optional<DenunciaDTO> obterDenunciaById(Integer idDenuncia) throws Exception {
        try {
            Optional<Denuncia> denunciaOpt = denunciaRepository.obterDenunciaById(idDenuncia);

            if (denunciaOpt.isPresent()) {
                Denuncia denuncia = denunciaOpt.get();
                DenunciaDTO dDto = objectMapper.convertValue(denuncia, DenunciaDTO.class);

                return Optional.of(dDto);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new Exception("Falha ao obter a denúncia pelo ID.", e);
        }
    }

    public Optional<DenunciaDTO> criarDenuncia(DenunciaCreateDTO denunciaDTO, int idUsuario) throws Exception {
        try {
            Denuncia d = objectMapper.convertValue(denunciaDTO, Denuncia.class);
            return Optional.of(objectMapper.convertValue(denunciaRepository.criarDenuncia(d, idUsuario), DenunciaDTO.class));
        } catch (Exception e){
            throw new Exception();
        }
    }

    public Optional<DenunciaDTO> editarDenuncia(DenunciaCreateDTO denunciaCreateDTO, Integer idDenuncia) throws Exception {
        try {
            Optional<Denuncia> denunciaOpt = denunciaRepository.obterDenunciaById(idDenuncia);

            if (denunciaOpt.isPresent()) {
                Denuncia d = objectMapper.convertValue(denunciaCreateDTO, Denuncia.class);
                return Optional.of(objectMapper.convertValue(denunciaRepository.editarDenuncia(idDenuncia, d), DenunciaDTO.class));
            }

            return Optional.empty();
        } catch (Exception e) {
            throw new Exception("Falha ao editar a denúncia.", e);
        }
    }

    public Optional<Boolean> deletarDenuncia(Integer idDenuncia, Integer idUsuario) throws Exception{
        try {

            return denunciaRepository.deletarDenuncia(idDenuncia, idUsuario);
        } catch (Exception e){
            throw new Exception();
        }
    }
}
