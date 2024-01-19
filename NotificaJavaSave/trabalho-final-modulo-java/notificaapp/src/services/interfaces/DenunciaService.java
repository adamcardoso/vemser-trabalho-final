package services.interfaces;

import models.Denuncia;

import java.util.List;
import java.util.Optional;

public interface DenunciaService {
    Optional<Denuncia> adicionarDenuncia(Denuncia denuncia);

    void editarDenuncia(Integer idDenuncia, Integer idUsuario, Denuncia denuncia);

    void removerDenuncia(Integer id);

    List<Denuncia> obterTodosFeed();

    Optional<List<Denuncia>> listarDenunciasDoUsuario(Integer idUsuario);
}
