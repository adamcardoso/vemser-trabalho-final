package views;

import java.util.ArrayList;

public class Home {
    CadastroUsuario cadastroUsuario;
    CadastroDenuncia cadastroDenuncia;
    public Home(){
        this.cadastroUsuario = new CadastroUsuario();
        this.cadastroDenuncia = new CadastroDenuncia();
    }

    public void iniciarSistema(){
        System.out.println("Sistema iniciado com sucesso!");
    }

    // TODO implementar a lógica de cadastro com scanf
    //  nas classes de cadastroUsuario e cadastroDenuncia
}
