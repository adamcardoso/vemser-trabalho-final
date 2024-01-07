package views;

import entities.Usuario;
import entities.enums.ClasseSocial;
import entities.enums.Etnia;
import entities.enums.Genero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstatisticaUsuario {

    public void exibirEstatisticas(List<Usuario> usuarios) {
        Map<ClasseSocial, Integer> classeSocialCount = new HashMap<>();
        Map<Etnia, Integer> etniaCount = new HashMap<>();
        Map<Genero, Integer> generoCount = new HashMap<>();

        System.out.println("------------- NOTIFICA -------------");
        for (Usuario usuario : usuarios) {
            classeSocialCount.put(usuario.getClasseSocial(), classeSocialCount.getOrDefault(usuario.getClasseSocial(), 0) + 1);
            etniaCount.put(usuario.getEtniaUsuario(), etniaCount.getOrDefault(usuario.getEtniaUsuario(), 0) + 1);
            generoCount.put(usuario.getGeneroUsuario(), generoCount.getOrDefault(usuario.getGeneroUsuario(), 0) + 1);
        }

        System.out.println("Classe social:");
        for (ClasseSocial classeSocial : ClasseSocial.values()) {
            System.out.println(classeSocial + ": " + (classeSocialCount.getOrDefault(classeSocial, 0) * 100.0 / usuarios.size()) + "%");
        }

        System.out.println("\nEtnia:");
        for (Etnia etnia : Etnia.values()) {
            System.out.println(etnia + ": " + (etniaCount.getOrDefault(etnia, 0) * 100.0 / usuarios.size()) + "%");
        }

        System.out.println("\nGênero:");
        for (Genero genero : Genero.values()) {
            System.out.println(genero + ": " + (generoCount.getOrDefault(genero, 0) * 100.0 / usuarios.size()) + "%");
        }
    }
}
