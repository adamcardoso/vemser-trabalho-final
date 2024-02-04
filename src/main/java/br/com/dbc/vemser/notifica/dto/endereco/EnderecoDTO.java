package br.com.dbc.vemser.notifica.dto.endereco;

import br.com.dbc.vemser.notifica.entity.enums.TipoEndereco;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    private TipoEndereco tipoEndereco;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String logradouro;

    private Integer numero;

    private String complemento;

    private String cep;

    private String cidade;

    private String estado;

    private String pais;

    private Integer idUsuario;
}
