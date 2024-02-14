package br.com.dbc.vemser.notifica.dto.instituicao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoDTO {
    private Integer idInstituicao;
    private String nomeInstituicao;
    private String emailInstituicao;
    private String celularInstituicao;
    private String senhaInstituicao;
}
