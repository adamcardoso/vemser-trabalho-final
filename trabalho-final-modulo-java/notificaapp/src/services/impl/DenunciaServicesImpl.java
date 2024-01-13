package services.impl;

import exceptions.DataBaseException;
import models.Denuncia;
import repositories.impl.DenunciaRepositoryImpl;
import services.interfaces.DenunciaService;

public class DenunciaServicesImpl implements DenunciaService {
    private DenunciaRepositoryImpl denunciaRepositoryImpl;

    public DenunciaServicesImpl() {
        denunciaRepositoryImpl = new DenunciaRepositoryImpl();
    }

    public void editarDenuncia(Integer id, Denuncia denuncia) {
        try {
            boolean conseguiuEditar = denunciaRepositoryImpl.editar(id, denuncia);
            System.out.println("pessoa editada? " + conseguiuEditar + "| com id=" + id);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Denuncia adicionar(Denuncia denuncia) {
        try {
            return denunciaRepositoryImpl.adicionar(denuncia);
        } catch (Exception e){
            System.out.println(e.getCause());
        }
        return null;
    }
}
