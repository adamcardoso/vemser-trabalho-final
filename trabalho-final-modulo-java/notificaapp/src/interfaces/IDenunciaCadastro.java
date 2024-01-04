package interfaces;

import entities.Denuncia;

import java.util.ArrayList;

public interface IDenunciaCadastro {
    Denuncia cadastrarDenuncia();

    Denuncia editarDenuncia(int idDenuncia, Denuncia denuncia);

    int excluirDenuncia(ArrayList<Denuncia> denuncia);

    void visualizarDenuncia(Denuncia denuncia);
}
