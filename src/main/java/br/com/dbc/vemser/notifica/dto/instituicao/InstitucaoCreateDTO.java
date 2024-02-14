package br.com.dbc.vemser.notifica.dto.instituicao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InstitucaoCreateDTO {
    private String nomeInstituicao;
    private String emailInstituicao;
    private String celularInstituicao;
    private String senhaInstituicao;
}
