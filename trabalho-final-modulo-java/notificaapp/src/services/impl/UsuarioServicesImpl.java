package services.impl;

import exceptions.DataBaseException;
import models.Usuario;
import repositories.impl.UsuarioRepositoryImpl;
import services.interfaces.UsuarioService;

import java.util.Objects;

public class UsuarioServicesImpl implements UsuarioService {
    private final UsuarioRepositoryImpl usuarioRepository;

    public UsuarioServicesImpl() {
        this.usuarioRepository = new UsuarioRepositoryImpl();
    }

    @Override
    public void listarUsuario(int idUsuario) {
        try {
            Usuario usuario = usuarioRepository.listarUsuario(idUsuario);
            imprimirUsuario(usuario);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public Usuario fazerLogin(String nomeUsuario, String senha) {
        try {
            Usuario usuario = usuarioRepository.fazerLogin(nomeUsuario, senha);
            if (Objects.nonNull(usuario)) {
                System.out.println("Login bem-sucedido para: " + usuario.getNomeUsuario());
                System.out.println("═════════════════════════════════════");
                return usuario;
            } else {
                System.out.println("Nome de usuário ou senha incorretos. Tente novamente.");
                System.out.println("═════════════════════════════════════");
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void imprimirUsuario(Usuario usuario) {
        System.out.println("═════════════════════════════════════");
        System.out.println("ID: " + usuario.getIdUsuario());
        System.out.println("Nome: " + usuario.getNomeUsuario());
        System.out.println("Número de Celular: " + usuario.getNumeroCelular());
        System.out.println("Senha: " + usuario.getSenhaUsuario());
        System.out.println("Etnia: " + usuario.getEtniaUsuario());
        System.out.println("Data de Nascimento: " + usuario.getDataNascimento());
        System.out.println("═════════════════════════════════════");
    }

    @Override
    public void remover(Integer id) {
        try {
            Usuario usuario = usuarioRepository.getUsuarioPorId(id);
            System.out.println("═════════════════════════════════════");
            if (usuario != null) {
                if (!usuario.getIsAdmin()) {
                    boolean usuarioRemovido = usuarioRepository.removerUsuario(id);

                    if (usuarioRemovido) {
                        System.out.println("Usuário removido com sucesso!");
                    } else {
                        System.out.println("Falha ao remover usuário!");
                    }
                } else {
                    System.out.println("Não é possível excluir um usuário administrador!");
                }
            } else {
                System.out.println("Usuário não encontrado!");
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        System.out.println("═════════════════════════════════════");
    }

    @Override
    public Usuario adicionar(Usuario usuario) {
        try {
            return usuarioRepository.adicionarUsuario(usuario);
        } catch (DataBaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void editarUsuario(Integer id, Usuario usuario){
        try {
            boolean conseguiuEditar = usuarioRepository.editar(id, usuario);
            System.out.println("pessoa editada? " + conseguiuEditar + "| com id=" + id);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

}


