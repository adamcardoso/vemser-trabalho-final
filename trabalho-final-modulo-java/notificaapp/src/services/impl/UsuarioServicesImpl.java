package services.impl;

import exceptions.DataBaseException;
import models.Usuario;
import repositories.impl.UsuarioRepositoryImpl;
import services.interfaces.UsuarioService;

import java.util.Objects;
import java.util.Optional;

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
    public Optional<Usuario> adicionar(Usuario usuario) {
        try {
            return Optional.of(usuarioRepository.adicionarUsuario(usuario));
        } catch (DataBaseException e) {
            System.out.println("Erro: "+ e.getCause());
        }
        return Optional.empty();
    }

    public void editarUsuario(Integer idUsuarioLogado, Usuario usuario){
        try {

            if(idUsuarioLogado == null){
                System.out.println("O id do usuario logado não pode ser nulo!");
                return;
            }

            if(usuario == null){
                System.out.println("O usuario a ser editado não pode ser nulo!");
                return;
            }
            Usuario usuarioExiste = usuarioRepository.getUsuarioPorId(idUsuarioLogado);

            if(usuarioExiste == null){
                System.out.println("O Usuário a ser editado não foi encontrado!");
            }

            boolean conseguiuEditar = usuarioRepository.editar(idUsuarioLogado, usuario);

            if (!conseguiuEditar) {
                System.out.println("Falha ao editar o usuario!");
                return;
            }
            System.out.println("Usuario editado com sucesso!");

        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Usuario> getUsuarioPorId(Integer idUsuario){
        try{
            return Optional.of(usuarioRepository.getUsuarioPorId(idUsuario));
        } catch (Exception e){
            System.out.println("Erro: " + e.getCause());
        }
        return Optional.empty();
    }
}


