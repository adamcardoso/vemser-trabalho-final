package services.impl;

import exceptions.DataBaseException;
import models.Denuncia;
import models.Usuario;
import models.enums.TipoUsuario;
import repositories.impl.AdminRepositoryImpl;
import repositories.impl.DenunciaRepositoryImpl;
import repositories.interfaces.AdminRepository;
import services.interfaces.AdminService;
import services.interfaces.UsuarioService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AdminServiceImpl implements AdminService {
    private AdminRepositoryImpl adminRepository;

    public AdminServiceImpl() {
        this.adminRepository = new AdminRepositoryImpl();
    }

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
    public void excluirDenuncia(int idDenuncia) {

        try {
            Denuncia denuncia = adminRepository.obterDenunciaPorId(idDenuncia);
            DenunciaRepositoryImpl denunciaRepository = new DenunciaRepositoryImpl();

            if (denuncia != null) {
                boolean denunciaRemovida = denunciaRepository.removerDenuncia(idDenuncia);

                if (denunciaRemovida) {
                    System.out.println("Denúncia removida com sucesso!");
                } else {
                    System.out.println("Falha ao remover a denúncia!");
                }
            } else {
                System.out.println("Denúncia não encontrada!");
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void listarDenuncias(Usuario usuarioLogado) {
        if (Objects.nonNull(usuarioLogado) && usuarioLogado.getTipoUsuario() == TipoUsuario.ADMIN) {
            AdminRepositoryImpl adminRepository = new AdminRepositoryImpl();
            try {
                List<Denuncia> denuncias = adminRepository.listarTodasDenuncias(usuarioLogado);

                if (denuncias.isEmpty()) {
                    System.out.println("Não há denúncias cadastradas.");
                } else {
                    System.out.print("\n");
                    System.out.println("══════ Lista de Denúncias ══════ ");
                    for (Denuncia denuncia : denuncias) {
                        imprimirTodasDenuncias(denuncia);
                    }
                }
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Acesso negado. Perfil de administrador requerido.");
        }
    }

    public Optional<Usuario> adicionarUsuario(Usuario u) {
        try {
            return Optional.of(adminRepository.adicionarUsuario(u));
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        return Optional.empty();
    }
    @Override
    public boolean editarDadosDoAdmin(Integer id, Usuario usuario) {
        try {
            if (Objects.nonNull(usuario) && Objects.nonNull(usuario.getTipoUsuario()) && usuario.getTipoUsuario() == TipoUsuario.ADMIN) {
                return adminRepository.editarDadosDoAdmin(id, usuario);
            } else {
                System.out.println("Acesso negado. Perfil de administrador requerido.");
                return false;
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void imprimirTodasDenuncias(Denuncia denuncia) {
        System.out.println("ID: " + denuncia.getIdDenuncia());
        System.out.println("Título: " + denuncia.getTitulo());
        System.out.println("Descrição: " + denuncia.getDescricao());
        System.out.println("Status: " + denuncia.getStatusDenuncia());
        System.out.println("Categoria: " + denuncia.getCategoria());
        System.out.println("Curtidas: " + denuncia.getCurtidas());
        System.out.println("Validar Denúncia: " + denuncia.getValidarDenuncia());

        if (denuncia.getTipoDenuncia() != null) {
            System.out.println("Tipo de Denúncia: " + denuncia.getTipoDenuncia());
        } else {
            System.out.println("Tipo de Denúncia: [Tipo não especificado]");
        }

        System.out.println("ID do Usuário: " + denuncia.getIdUsuario());
        System.out.println("════════════════════════════");
    }

    private void imprimirTodosUsuario(Usuario usuario) {
        System.out.println("ID: " + usuario.getIdUsuario());
        System.out.println("Nome: " + usuario.getNomeUsuario());
        System.out.println("Número de Celular: " + usuario.getNumeroCelular());
        System.out.println("Senha: " + usuario.getSenhaUsuario());
        System.out.println("Etnia: " + usuario.getEtniaUsuario());
        System.out.println("Data de Nascimento: " + usuario.getDataNascimento());
        System.out.println("Admin: " + (usuario.getIsAdmin() ? "Sim" : "Não"));
        System.out.println("═════════════════════════════════════");
    }

}
