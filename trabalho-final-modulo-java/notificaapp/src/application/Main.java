package application;

import models.enums.Categoria;
import views.Home;

public class Main {
    public static void main(String[] args) {
        Home home = new Home();
        home.exibirLoginMenu();
    }
}