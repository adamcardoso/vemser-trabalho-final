package application;

import exceptions.DataBaseException;
import models.Denuncia;
import models.Localizacao;
import models.Usuario;
import models.enums.Categoria;
import models.enums.TipoDenuncia;
import repositories.impl.DenunciaRepositoryImpl;
import views.Home;

public class Main {
    public static void main(String[] args) throws DataBaseException {
        Home home = new Home();
        home.exibirLoginMenu();
    }
}