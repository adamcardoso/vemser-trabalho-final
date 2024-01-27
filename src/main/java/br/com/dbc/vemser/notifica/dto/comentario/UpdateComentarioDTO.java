package br.com.dbc.vemser.notifica.dto.comentario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateComentarioDTO {
    private Integer idComentario;
    private String comentario;
    private int curtida;
    private Integer idDenuncia;
    private Integer idUsuario;

    public UpdateComentarioDTO(String comentario){
        this.comentario = comentario;
    }
}

