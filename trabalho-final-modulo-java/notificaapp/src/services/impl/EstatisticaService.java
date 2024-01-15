package services.impl;

import exceptions.DataBaseException;
import models.Usuario;
import repositories.impl.EstatisticaRepositoryImpl;
import views.Estatistica;

import java.util.List;

public class EstatisticaService {

    public void exibirEstatisticasUsuarios() {
        EstatisticaRepositoryImpl estatisticaRepository = new EstatisticaRepositoryImpl();
        try {
            List<Usuario> usuarios = estatisticaRepository.listarTodosUsuarios();

            Estatistica estatistica = new Estatistica();
            estatistica.exibirEstatisticas(usuarios);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

}
