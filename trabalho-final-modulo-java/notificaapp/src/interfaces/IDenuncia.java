package interfaces;

import application.Denuncia;

public interface IDenuncia {
    void CadastrarDenuncia(Denuncia denuncia);
    void EditarDenuncia(int idDenuncia, Denuncia novaDenuncia);
    boolean ValidarDenuncia(int idDenuncia);
    void ExcluirDenuncia(int idDenuncia);
}
