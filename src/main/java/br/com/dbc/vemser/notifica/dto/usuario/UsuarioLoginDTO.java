package br.com.dbc.vemser.notifica.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Schema(description = "Objeto de dados para autenticação de usuário")
public class UsuarioLoginDTO {

    @Schema(description = "E-mail do usuário", required = true, example = "usuario@example.com")
    private String emailUsuario;

    @Schema(description = "Senha do usuário", required = true, example = "Senha123@")
    private String senhaUsuario;

}
