package application;

import exceptions.DataBaseException;
import models.Denuncia;
import models.Localizacao;
import models.Usuario;
import models.enums.Categoria;
import models.enums.TipoDenuncia;
import services.impl.DenunciaServicesImpl;
import views.Home;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args){
        Home home = new Home();
        home.exibirLoginMenu();
    }
}