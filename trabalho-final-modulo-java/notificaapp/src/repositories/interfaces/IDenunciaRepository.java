package repositories.interfaces;

import models.Denuncia;

import java.util.List;
import java.util.Optional;

public interface IDenunciaRepository {
    List<Denuncia> obterTodos();
}
