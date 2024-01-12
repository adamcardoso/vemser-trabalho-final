package services.impl;

import exceptions.DataBaseException;
import models.Usuario;
import models.enums.TipoUsuario;
import repositories.impl.AdminRepositoryImpl;
import repositories.impl.UsuarioRepositoryImpl;
import services.interfaces.UsuarioService;
import views.EstatisticaUsuario;

import java.text.DecimalFormat;
import java.util.List;

public class UsuarioServicesImpl implements UsuarioService {
    private final UsuarioRepositoryImpl usuarioRepository;

    public UsuarioServicesImpl() {
        this.usuarioRepository = new UsuarioRepositoryImpl();
    }

    public void listarUsuario(int idUsuario) {
        try {
            Usuario usuario = usuarioRepository.listarUsuario(idUsuario);
            imprimirUsuario(usuario);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public Usuario fazerLogin(String nomeUsuario, String senha){
        try {
            Usuario usuario = usuarioRepository.fazerLogin(nomeUsuario, senha);
            if (usuario != null) {
                System.out.println("Login bem-sucedido!");
                setIsAdmin(usuario);
                return usuario;
            } else {
                System.out.println("Nome de usuário ou senha incorretos. Tente novamente.");
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void imprimirUsuario(Usuario usuario) {
        System.out.println("ID: " + usuario.getIdUsuario());
        System.out.println("Nome: " + usuario.getNomeUsuario());
        System.out.println("Número de Celular: " + usuario.getNumeroCelular());
        System.out.println("Senha: " + usuario.getSenhaUsuario());
        System.out.println("Etnia: " + usuario.getEtniaUsuario());
        System.out.println("Data de Nascimento: " + usuario.getDataNascimento());
        System.out.println("------------------------");
    }

    public void remover(Integer id) {
        try {
            boolean usuarioExiste = usuarioRepository.usuarioExiste(id);

            if (usuarioExiste) {
                boolean usuarioRemovido = usuarioRepository.remover(id);

                if (usuarioRemovido) {
                    System.out.println("Usuário removido com sucesso!");
                } else {
                    System.out.println("Falha ao remover usuário!");
                }
            } else {
                System.out.println("Usuário não encontrado!");
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    private void setIsAdmin(Usuario usuario) {
        if(usuario.getTipoUsuario().equals(TipoUsuario.ADMIN)) {
            usuario.setIsAdmin(true);
        } else {
            usuario.setIsAdmin(false);
        }
    }

    public Usuario adicionar(Usuario usuario) {

        return null;
    }

    public void exibirEstatisticasUsuarios() {
        AdminRepositoryImpl adminRepository = new AdminRepositoryImpl();
        try {
            List<Usuario> usuarios = adminRepository.listarTodosUsuarios();

            //Chama a classe de estatísticas e exibe as estatísticas
            EstatisticaUsuario estatisticaUsuario = new EstatisticaUsuario();
            estatisticaUsuario.exibirEstatisticas(usuarios);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

}


