package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
public class DenunciaService {
    private DenunciaRepository denunciaRepository;

    public DenunciaService(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    public List<Denuncia> list() {
        return denunciaRepository.list();
    }

    public List<Denuncia> listByTitulo(String titulo) {
        return denunciaRepository.listByTitulo(titulo);
    }

    public Denuncia criarDenuncia(Denuncia denuncia, int idUsuario) throws RegraDeNegocioException {
        return denunciaRepository.criarDenuncia(denuncia, idUsuario);

    }

    private Denuncia getIdUsuario(Integer id) throws Exception {
        Denuncia denunciaIdUsuario = denunciaRepository.list().stream()
                .filter(denuncia -> denuncia.getIdUsuario() == id)
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Impossível criar Denúncia em um Usuário não encontrado!"));
        return denunciaIdUsuario;
    }

    public List<Denuncia> ListByIdUsuario(String idUsuario) {
        return denunciaRepository.ListByIdUsuario(idUsuario);
    }
}
