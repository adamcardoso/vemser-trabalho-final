package br.com.dbc.vemser.notifica.dto.endereco;

import br.com.dbc.vemser.notifica.entity.enums.TipoEndereco;
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
public class EnderecoDTO {
    private Integer idEndereco;

    @NotNull(message = "Tipo de Endereco não pode ser nulo")
    private TipoEndereco tipoEndereco;

    @NotEmpty(message = "Logradouro não pode ser nulo")
    private String logradouro;

    private Integer numero;

    private String complemento;

    @Size(min = 8, max = 8, message = "Cep deve conter 8 caracteres")
    private String cep;

    @NotEmpty(message = "Cidade não pode ser nulo")
    private String cidade;

    @NotEmpty(message = "Estado não pode ser nulo")
    private String estado;

    @NotEmpty(message = "País não pode ser nulo")
    private String pais;

    private Integer idUsuario;
}
