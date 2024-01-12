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
//        Home home = new Home();
//        home.exibirLoginMenu();


            DenunciaRepositoryImpl denunciaRepository = new DenunciaRepositoryImpl();
            Denuncia d = denunciaRepository.adicionar(new Denuncia("Ainda sem água", "Estamos sem água de novo aqui no bairro", Categoria.getEnum("1"), TipoDenuncia.getEnum("1"), 1L));

            System.out.printf("""
                    titulo: %s
                    descrição: %s
                    data hora: %s
                    situacao: %s
                    categoria: %s
                    curtida: %s
                    validar denuncia: %s
                    tipo denuncia: %s
                    %n""", d.getTitulo(), d.getDescricao(), d.getDataHora(), d.getStatusDenuncia(), d.getCategoria(), d.getCurtidas(), d.getValidarDenuncia(), d.getTipoDenuncia());

    }
}