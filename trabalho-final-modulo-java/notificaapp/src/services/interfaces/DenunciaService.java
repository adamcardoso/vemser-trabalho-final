package services.interfaces;

import models.Denuncia;

import java.util.List;

public interface DenunciaService {
    Denuncia adicionarDenuncia(Denuncia denuncia);

    void editarDenuncia(Integer idDenuncia, Integer idUsuario, Denuncia denuncia);

    void removerDenuncia(Integer id);

    List<Denuncia> obterTodos();
}
