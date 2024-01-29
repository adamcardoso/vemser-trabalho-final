package br.com.dbc.vemser.notifica.dto.comentario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ComentarioDTO {
    private Integer idComentario;
    private String comentario;
    private Integer curtida;
    private Integer idDenuncia;
    private Integer idUsuario;
}
