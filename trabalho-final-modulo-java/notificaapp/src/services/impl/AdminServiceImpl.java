package services.impl;

import exceptions.DataBaseException;
import models.Denuncia;
import models.Usuario;
import models.enums.TipoUsuario;
import repositories.impl.AdminRepositoryImpl;
import services.interfaces.AdminService;

import java.util.List;
import java.util.Objects;

public class AdminServiceImpl implements AdminService {


    @Override
    public void listarUsuarios(Usuario usuarioLogado) {
        if (Objects.nonNull(usuarioLogado) && usuarioLogado.getTipoUsuario() == TipoUsuario.ADMIN) {
            AdminRepositoryImpl adminRepository = new AdminRepositoryImpl();
            try {
                List<Usuario> usuarios = adminRepository.listarTodosUsuarios(usuarioLogado);
                for (Usuario usuario : usuarios) {
                    imprimirTodosUsuario(usuario);
                }
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Acesso negado. Perfil de administrador requerido.");
        }
    }

    @Override
    public void listarDenuncias(Usuario usuarioLogado) {
        if (Objects.nonNull(usuarioLogado) && usuarioLogado.getTipoUsuario() == TipoUsuario.ADMIN) {
            AdminRepositoryImpl adminRepository = new AdminRepositoryImpl();
            try {
                List<Denuncia> denuncias = adminRepository.listarTodasDenuncias(usuarioLogado);
                for (Denuncia denuncia : denuncias) {
                    imprimirDenuncia(denuncia);
                }
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Acesso negado. Perfil de administrador requerido.");
        }
    }

    private void imprimirDenuncia(Denuncia denuncia) {
        System.out.println("ID: " + denuncia.getIdDenuncia());
        System.out.println("Título: " + denuncia.getTitulo());
        System.out.println("Descrição: " + denuncia.getDescricao());
        System.out.println("Status: " + denuncia.getStatusDenuncia().getValor());
        System.out.println("Categoria: " + denuncia.getCategoria().getValor());
        System.out.println("Curtidas: " + denuncia.getCurtidas());
        System.out.println("Validar Denúncia: " + denuncia.getValidarDenuncia());
        System.out.println("Tipo de Denúncia: " + denuncia.getTipoDenuncia().getValor());
        System.out.println("ID do Usuário: " + denuncia.getIdUsuario());
        System.out.println("------------------------");
    }

    private void imprimirTodosUsuario(Usuario usuario) {
        System.out.println("ID: " + usuario.getIdUsuario());
        System.out.println("Nome: " + usuario.getNomeUsuario());
        System.out.println("Número de Celular: " + usuario.getNumeroCelular());
        System.out.println("Senha: " + usuario.getSenhaUsuario());
        System.out.println("Etnia: " + usuario.getEtniaUsuario());
        System.out.println("Data de Nascimento: " + usuario.getDataNascimento());
        System.out.println("------------------------");
    }
}
