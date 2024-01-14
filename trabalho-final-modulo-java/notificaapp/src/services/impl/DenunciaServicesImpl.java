package services.impl;

import exceptions.DataBaseException;

import java.util.Collections;

import models.Denuncia;
import models.Usuario;
import repositories.impl.AdminRepositoryImpl;
import repositories.impl.DenunciaRepositoryImpl;
import repositories.impl.UsuarioRepositoryImpl;
import repositories.interfaces.UsuarioRepository;
import services.interfaces.DenunciaService;
import services.interfaces.UsuarioService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class DenunciaServicesImpl implements DenunciaService {
    private final DenunciaRepositoryImpl denunciaRepositoryImpl;
    private final AdminRepositoryImpl adminRepository;

    public DenunciaServicesImpl() {
        denunciaRepositoryImpl = new DenunciaRepositoryImpl();
        adminRepository = new AdminRepositoryImpl();
    }

    @Override
    public Optional<Denuncia> adicionarDenuncia(Denuncia denuncia) {
        try {
            return Optional.of(denunciaRepositoryImpl.adicionar(denuncia));
        } catch (Exception e){
            System.out.println("Erro: "+ e.getCause());
        }
        return Optional.empty();
    }

    @Override
    public void editarDenuncia(Integer id, Integer idUsuario, Denuncia denuncia) {
        try {
            if(id == null){
                System.out.println("O campo id precisa ser diferente de nulo");
                return;
            }

            if(idUsuario == null){
                System.out.println("O campo id usuario ser diferente de nulo");
                return;
            }

            if(denuncia == null){
                System.out.println("A denuncia precisa ser diferente de nulo");
                return;
            }

            Denuncia denunciaPorId = adminRepository.obterDenunciaPorId(id);

            if (denunciaPorId == null) {
                System.out.println("Denúncia não encontrada!");
                return;
            }

            if (denunciaPorId.getUsuario().getIdUsuario() != idUsuario) {
                System.out.println("Você não pode editar a denúncia de outro usuário!");
                return;
            }
            boolean conseguiuEditar = denunciaRepositoryImpl.editar(id, denuncia);

            if (!conseguiuEditar) {
                System.out.println("Falha ao remover a denúncia!");
                return;
            }
            System.out.println("Denuncia editada com sucesso!");

        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public Denuncia obterDenunciaPorId(Integer id, Integer idUsuario){
        try{
            Denuncia denunciaPorId = adminRepository.obterDenunciaPorId(id);

            if (denunciaPorId == null) {
                System.out.println("Denúncia não encontrada!");
                return null;
            }

            if (denunciaPorId.getUsuario().getIdUsuario() != idUsuario) {
                System.out.println("Você não pode visualizar a denúncia de outro usuário!");
                return null;
            }
            return denunciaPorId;
        }catch (DataBaseException e){
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void removerDenuncia(Integer idDenuncia) {
        try {
            boolean conseguiuRemover = denunciaRepositoryImpl.removerDenuncia(idDenuncia);
            System.out.println("Denúncia removida? " + conseguiuRemover + "| com id=" + idDenuncia);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Denuncia> obterTodos() {
        try {
            return denunciaRepositoryImpl.obterTodos();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<List<Denuncia>> listarDenunciasDoUsuario(Integer idUsuario){
        try{
            UsuarioService usuarioService = new UsuarioServicesImpl();
            Optional<Usuario> uOpt = usuarioService.getUsuarioPorId(idUsuario);

            if(uOpt.isEmpty())
                return Optional.empty();

            return Optional.of(denunciaRepositoryImpl.listarDenunciasDoUsuario(idUsuario));
        }  catch (Exception e){
            System.out.println("Erro: "+ e.getCause());
        }
        return Optional.empty();
    }
}
