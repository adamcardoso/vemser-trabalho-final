package interfaces;

import entities.Denuncia;

public interface IDenuncia {
    void cadastrarDenuncia(Denuncia denuncia);
    void editarDenuncia(int idDenuncia, Denuncia novaDenuncia);
    boolean validarDenuncia(int idDenuncia);
    void excluirDenuncia(int idDenuncia);
}
