package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioLoginDTO;
import br.com.dbc.vemser.notifica.exceptions.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ILoginController {
    @Operation(summary = "Fazer Login", description = "Fazer Login com Email e Senha, se os dados estiverem no banco de dados ira logar.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os Dados do usuário que Logou.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioCreateDTO.class))),
                    @ApiResponse(responseCode = "401", description = "message: Credenciais inválidas, Usuário ou Senha Incorretos!",
                            content = @Content(schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
            }

    )
    @PostMapping
    public ResponseEntity<Object> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO);
}

