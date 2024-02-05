package br.com.dbc.vemser.notifica.dto.relatorio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RelatorioDto {
    private Integer idUsuario;
    private String nomeUsuario;
    private Integer curtidasDenuncia;
    private Integer curtiasComentario;
}
