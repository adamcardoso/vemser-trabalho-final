package views;

import entities.Usuario;

import java.util.ArrayList;

public class Home {
    ArrayList<Usuario> cadastroUsuario;
    ArrayList<Usuario> cadastroDenuncia;
    public Home(){
        this.cadastroUsuario = new ArrayList<>();
        this.cadastroDenuncia = new ArrayList<>();
    }

    public void iniciarSistema(){

        System.out.println("Sistema iniciado com sucesso!");
    }

    // TODO implementar a lógica de cadastro com scanf
    //  nas classes de cadastroUsuario e cadastroDenuncia
}
