package services.impl;

import exceptions.DataBaseException;
import models.Usuario;
import repositories.impl.AdminRepositoryImpl;

import java.util.List;

public class AdminServiceImpl {


    public void listarUsuarios() {
        AdminRepositoryImpl adminRepository = new AdminRepositoryImpl();
        try {
            List<Usuario> usuarios = adminRepository.listarTodosUsuarios();
            for (Usuario usuario : usuarios) {
                imprimirTodosUsuario(usuario);
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
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
