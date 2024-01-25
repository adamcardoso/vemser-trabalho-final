package br.com.dbc.vemser.notifica.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Comentario {
    private Integer idComentario;
    private String comentario;
    private int curtida;
    private Integer idDenuncia;
    private Integer idUsuario;

    public Comentario(String comentario, Integer idDenuncia, Integer idUsuario){
        this.comentario = comentario;
        this.curtida = 0;
        this.idDenuncia = idDenuncia;
        this.idUsuario = idUsuario;
    }
}