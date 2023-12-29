package application;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

public class Denuncia {
    int idDenuncia;
    String descricao;
    Localizacao local;
    Usuario usuario;
    LocalDateTime dataHora;
    String statusDenuncia;
}
