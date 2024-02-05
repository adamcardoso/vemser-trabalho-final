package br.com.dbc.vemser.notifica.dto.curtida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurtidaDto {
    private Integer idCurtida;
    private LocalDateTime dataHora;
    private Integer idUsuario;
    private Integer idDenuncia;
    private Integer idComentario;
}
