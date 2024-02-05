package br.com.dbc.vemser.notifica.dto.localizacao;

import br.com.dbc.vemser.notifica.entity.Denuncia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocalizacaoDto {
    @Schema(description = "ID da Localização", example = "1")
    private Integer idLocalizacao;

    @Schema(description = "Latitude da Localização", example = "56,2482")
    private String latitude;

    @Schema(description = "Longitude da Localização", example = "26,6384")
    private String longitude;

    @Schema(description = "ID da Denúncia", example = "1")
    private Integer idDenuncia;
}
