package br.com.dbc.vemser.notifica.dto.instituicao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInstituicaoDTO {
    @Schema(description = "E-mail da Instituição", required = true, example = "corsan@example.com")
    private String emailInstituicao;
    @Schema(description = "Senha da Instituição", required = true, example = "Senha123@")
    private String senhaInstituicao;

}
