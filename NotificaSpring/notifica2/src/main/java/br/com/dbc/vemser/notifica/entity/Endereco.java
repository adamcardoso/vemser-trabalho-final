package br.com.dbc.vemser.notifica.entity;

import br.com.dbc.vemser.notifica.entity.enums.TipoEndereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Endereco {
    private Integer idEndereco;
    private TipoEndereco tipoEndereco;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private Integer idPessoa;
}
