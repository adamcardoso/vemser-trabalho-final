package application;

import models.Usuario;
import models.enums.ClasseSocial;
import models.enums.Etnia;
import models.enums.Genero;
import models.enums.TipoUsuario;
import services.impl.UsuarioServicesImpl;
import views.Home;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Home home = new Home();
        home.exibirLoginMenu();
    }
}