package br.com.dbc.vemser.notifica.entity;

import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Estatistica {
    private Etnia etniaUsuario;

    private ClasseSocial classeSocial;

    private Genero generoUsuario;

    private Integer total;

    private double percentual;

    private LocalDate ultimaDataCadastro;
}
