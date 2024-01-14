package application;
import exceptions.DataBaseException;
import views.Home;

public class Main {
    public static void main(String[] args) throws DataBaseException {
        Home home = new Home();
        home.exibirLoginMenu();
    }
}