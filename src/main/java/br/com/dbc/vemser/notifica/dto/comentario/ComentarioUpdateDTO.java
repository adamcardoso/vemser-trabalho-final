package br.com.dbc.vemser.notifica.dto.comentario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ComentarioUpdateDTO {
    private Integer idComentario;
    private String comentario;
    private Integer curtida;
    private Integer idDenuncia;
    private Integer idUsuario;

    public ComentarioUpdateDTO(String comentario){
        this.comentario = comentario;
    }
}

