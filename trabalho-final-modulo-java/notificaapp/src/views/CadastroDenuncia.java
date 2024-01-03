package views;

import entities.Denuncia;
import entities.Localizacao;
import entities.Usuario;
import entities.enums.Situacao;
import interfaces.IDenunciaCadastro;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CadastroDenuncia implements IDenunciaCadastro {

    @Override
    public Denuncia cadastrarDenuncia(Denuncia denuncia) {
        return null;
    }

    @Override
    public Denuncia editarDenuncia(int idDenuncia) {
        return null;
    }

    @Override
    public int excluirDenuncia(ArrayList<Denuncia> denuncia) {
        return 0;
    }

    @Override
    public void visualizarDenuncia() {

    }
}
