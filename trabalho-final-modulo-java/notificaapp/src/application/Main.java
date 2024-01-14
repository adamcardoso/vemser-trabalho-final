package application;

import models.Denuncia;
import models.enums.Categoria;
import repositories.impl.DenunciaRepositoryImpl;
import services.impl.DenunciaServicesImpl;
import services.interfaces.DenunciaService;
import views.Home;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Home home = new Home();
        home.exibirLoginMenu();
    }
}