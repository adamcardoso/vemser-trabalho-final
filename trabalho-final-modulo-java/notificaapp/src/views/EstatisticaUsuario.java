package views;

import models.Usuario;
import models.enums.ClasseSocial;
import models.enums.Etnia;
import models.enums.Genero;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstatisticaUsuario {

    public void exibirEstatisticas(List<Usuario> usuarios) {
        Map<ClasseSocial, Integer> classeSocialCount = new HashMap<>();
        Map<Etnia, Integer> etniaCount = new HashMap<>();
        Map<Genero, Integer> generoCount = new HashMap<>();

        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("------------- Estatísticas -------------");
        for (Usuario usuario : usuarios) {
            classeSocialCount.put(usuario.getClasseSocial(), classeSocialCount.getOrDefault(usuario.getClasseSocial(), 0) + 1);
            etniaCount.put(usuario.getEtniaUsuario(), etniaCount.getOrDefault(usuario.getEtniaUsuario(), 0) + 1);
            generoCount.put(usuario.getGeneroUsuario(), generoCount.getOrDefault(usuario.getGeneroUsuario(), 0) + 1);
        }

        System.out.println("Classe social:");
        for (ClasseSocial classeSocial : ClasseSocial.values()) {
            double porcentagem = (classeSocialCount.getOrDefault(classeSocial, 0) * 100.0 / usuarios.size());
            String formattedporcentagem = df.format(porcentagem);
            System.out.println(classeSocial + ": " + formattedporcentagem + "%");
        }

        System.out.println("\nEtnia:");
        for (Etnia etnia : Etnia.values()) {
            double porcentagem = (etniaCount.getOrDefault(etnia, 0) * 100.0 / usuarios.size());
            String formattedporcentagem = df.format(porcentagem);
            System.out.println(etnia + ": " + formattedporcentagem + "%");
        }

        System.out.println("\nGênero:");
        for (Genero genero : Genero.values()) {
            double porcentagem = (generoCount.getOrDefault(genero, 0) * 100.0 / usuarios.size());
            String formattedporcentagem = df.format(porcentagem);
            System.out.println(genero + ": " + formattedporcentagem + "%");
        }
    }
}
