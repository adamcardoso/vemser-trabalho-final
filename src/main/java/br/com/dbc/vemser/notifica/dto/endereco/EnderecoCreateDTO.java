package br.com.dbc.vemser.notifica.dto.endereco;

import br.com.dbc.vemser.notifica.entity.enums.TipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoCreateDTO {

    @Schema(description = "Tipo de Endereço", example = "RESIDENCIAL", allowableValues = {"RESIDENCIAL", "COMERCIAL"})
    private TipoEndereco tipoEndereco;

    @Schema(description = "Logradouro", example = "Rua das Flores")
    private String logradouro;

    @Schema(description = "Número", example = "123")
    private Integer numero;

    @Schema(description = "Complemento", example = "Apto 4")
    private String complemento;

    @Schema(description = "Bairro", example = "Centro")
    private String bairro;

    @Schema(description = "CEP", example = "12345-678")
    private String cep;

    @Schema(description = "Cidade", example = "São Paulo")
    private String cidade;

    @Schema(description = "Estado", example = "SP")
    private String estado;

    @Schema(description = "País", example = "Brasil")
    private String pais;

    @Schema(description = "Latitude", example = "-23.550520")
    private String latitude;

    @Schema(description = "Longitude", example = "-46.633308")
    private String longitude;

    public boolean isEnderecoInformado() {
        return (logradouro != null && numero != null && bairro != null && cep != null && cidade != null && estado != null && pais != null) ||
                (latitude != null && longitude != null);
    }
}
