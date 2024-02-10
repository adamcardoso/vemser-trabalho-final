package br.com.dbc.vemser.notifica.dto.comentario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ComentarioCreateDTO {

    @Schema(description = "Texto do Comentário", example = "Isso é um comentário")
    private String comentario;

    @Schema(description = "Número de Curtidas", example = "10")
    private Integer curtida;

    @Schema(description = "ID da Denúncia associada ao Comentário", example = "1")
    private Integer idDenuncia;

    @Schema(description = "ID do Usuário que fez o Comentário", example = "1")
    private Integer idUsuario;

    public ComentarioCreateDTO(String comentario, Integer idDenuncia, Integer idUsuario){
        this.comentario = comentario;
        this.curtida = 0;
        this.idDenuncia = idDenuncia;
        this.idUsuario = idUsuario;
    }
}
