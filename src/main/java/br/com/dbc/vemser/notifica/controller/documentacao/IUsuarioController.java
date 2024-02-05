package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.exceptions.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IUsuarioController {

    @Operation(summary = "Obter usuário por ID", description = "Obtém um usuário pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o usuário"),
                    @ApiResponse(responseCode = "400", description = "Usuário não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obterUsuarioById(@PathVariable("idUsuario") Integer idUsuario) throws Exception;

    @Operation(summary = "Criar usuário", description = "Cria um novo usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a o Usuário criado!"),
                    @ApiResponse(responseCode = "400 ", description = "Object: não deve ser nulo",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))

            }
    )
    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioCreateDTO novoUsuario) throws Exception;
    @Operation(summary = "Atualizar usuário", description = "Atualiza um usuário pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a o Usuário criado!",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DenunciaCreateDTO.class))),
                    @ApiResponse(responseCode = "400 ", description = "Object: não deve ser nulo",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable("idUsuario") Integer idUsuario,@RequestBody UsuarioUpdateDTO novoUsuario) throws Exception;
    @Operation(summary = "Remover usuário", description = "Remove um usuário pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Usuário não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> removerUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception;
}
