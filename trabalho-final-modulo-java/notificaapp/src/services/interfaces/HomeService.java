package services.interfaces;

import models.Usuario;

import java.util.Scanner;

public interface HomeService {
    void feed();
    void denunciaForm(Usuario u, Scanner s);
    void cadastroUsuarioForm(Scanner s);
    void cadastroUsuarioFormByAdmin(Usuario u, Scanner s);
    void listarDenunciasDoUsuario(Usuario u);
}
