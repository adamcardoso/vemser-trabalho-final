package application;
import exceptions.DataBaseException;
import models.Usuario;
import models.enums.ClasseSocial;
import models.enums.Etnia;
import models.enums.Genero;
import models.enums.TipoUsuario;
import repositories.impl.AdminRepositoryImpl;
import services.impl.AdminServiceImpl;
import services.interfaces.AdminService;
import views.Home;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws DataBaseException {
        Home home = new Home();
        home.exibirLoginMenu();
    }
}