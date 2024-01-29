package br.com.dbc.vemser.notifica.dto.estatistica;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EstatisticaDTO {
    @Schema(description = "Classificação da Estatística", example = "Alta")
    private String classificacao;

    @Schema(description = "Total da Estatística", example = "100")
    private Integer total;

    @Schema(description = "Percentual da Estatística", example = "25.5")
    private double percentual;
}
