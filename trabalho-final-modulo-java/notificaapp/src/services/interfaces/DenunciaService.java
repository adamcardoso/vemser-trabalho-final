package services.interfaces;

import models.Denuncia;

import java.util.List;
import java.util.Optional;

public interface DenunciaService {
    Denuncia adicionar(Denuncia denuncia);
    List<Denuncia> obterTodos();
}
