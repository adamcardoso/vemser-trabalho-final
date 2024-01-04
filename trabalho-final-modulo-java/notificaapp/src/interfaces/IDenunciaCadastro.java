package interfaces;

import entities.Denuncia;

import java.util.HashMap;

public interface IDenunciaCadastro {
    Denuncia cadastrarDenuncia();

    Denuncia editarDenuncia(int idDenuncia, Denuncia denuncia);

    void excluirDenuncia(int idDenuncia, HashMap<Integer, Denuncia> denuncias);

    void visualizarDenuncia(Denuncia denuncia);
}
