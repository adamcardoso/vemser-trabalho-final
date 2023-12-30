package entities;

import java.time.LocalDateTime;

public class Denuncia {
    int idDenuncia;
    String descricao;
    Localizacao local;
    Usuario usuario;
    LocalDateTime dataHora;
    String statusDenuncia;
}
