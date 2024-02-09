package br.com.dbc.vemser.notifica.dto.localizacao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocalizacaoCreateDTO {
    @Schema(description = "Latitude da Localização", example = "-29.972077")
    private String latitude;

    @Schema(description = "Longitude da Localização", example = "-51.728250")
    private String longitude;
}
