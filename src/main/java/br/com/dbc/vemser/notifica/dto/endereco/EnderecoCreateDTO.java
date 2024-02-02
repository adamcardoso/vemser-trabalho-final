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
    @NotNull(message = "Tipo de Endereco não pode ser nulo")
    @Schema(description = "Tipo de Endereço", required = true, example = "RESIDENCIAL", allowableValues = {"RESIDENCIAL", "COMERCIAL"})
    private TipoEndereco tipoEndereco;

    @Schema(description = "Número do Endereço", example = "123")
    private Integer numero;

    @Schema(description = "Complemento do Endereço", example = "Apto 4")
    private String complemento;

    @Size(min = 8, max = 8, message = "Cep deve conter 8 dígitos")
    @Schema(description = "CEP do Endereço", example = "12345678")
    private String cep;

    @NotEmpty(message = "País não pode ser nulo")
    @Schema(description = "País do Endereço", required = true, example = "Brasil")
    private String pais;

    @Schema(description = "ID da Pessoa associada ao Endereço", example = "1")
    private Integer idUsuario;
}
