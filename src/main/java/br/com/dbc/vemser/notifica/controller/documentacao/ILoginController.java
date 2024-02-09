package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioLoginDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.exceptions.ErrorResponse;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
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
                    @ApiResponse(responseCode = "400", description = "message: Credenciais inválidas, Usuário ou Senha Incorretos!",
                            content = @Content(schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
            }

    )
    @PostMapping
    public ResponseEntity<String> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) throws RegraDeNegocioException;

    @Operation(summary = "Fazer Cadastro", description = "Fazer Cadastro com Email e Senha, se os dados não forem existentes no banco de dados ira Cadastrar.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os Dados do usuário que Cadastrou.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioCreateDTO.class))),
                    @ApiResponse(responseCode = "400", description = "message: Credenciais inválidas, Usuário Já Cadastrado!",
                            content = @Content(schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
            }

    )
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException ;

    @Operation(summary = "Pegar Cadastro", description = "Pegar os Dados do Usuário que está logado.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os Dados do usuário.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioCreateDTO.class)))
            }

    )
    public ResponseEntity<Usuario> usuarioLogado() throws RegraDeNegocioException;
}

