package services.impl;

import exceptions.DataBaseException;
import java.util.Collections;
import models.Denuncia;
import repositories.impl.DenunciaRepositoryImpl;
import services.interfaces.DenunciaService;

import java.util.List;

public class DenunciaServicesImpl implements DenunciaService {
    private final DenunciaRepositoryImpl denunciaRepositoryImpl;

    public DenunciaServicesImpl() {
        denunciaRepositoryImpl = new DenunciaRepositoryImpl();
    }

    @Override
    public Denuncia adicionarDenuncia(Denuncia denuncia) {
        try {
            return denunciaRepositoryImpl.adicionar(denuncia);
        } catch (Exception e){
            System.out.println("Erro: "+ e.getCause());
        }

        return null;
    }

    @Override
    public void editarDenuncia(Integer id, Denuncia denuncia) {
        try {
            boolean conseguiuEditar = denunciaRepositoryImpl.editar(id, denuncia);
            System.out.println("pessoa editada? " + conseguiuEditar + "| com id=" + id);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerDenuncia(Integer idDenuncia){
        try {
            boolean conseguiuRemover = denunciaRepositoryImpl.removerDenuncia(idDenuncia);
            System.out.println("Denúncia removida? " + conseguiuRemover + "| com id=" + idDenuncia);
        }catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Denuncia> obterTodos() {
        try{
            return denunciaRepositoryImpl.obterTodos();
        } catch (Exception e){
            System.out.println("Erro: "+ e.getCause());
            return Collections.emptyList();
        }
    }
}
