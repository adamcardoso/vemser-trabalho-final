package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.exceptions.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface IDenunciaController {


    @Operation(summary = "Obter denúncia por ID", description = "Esse Método Obtém uma denúncia pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a denúncia",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DenunciaDTO.class))),
                    @ApiResponse(responseCode = "401", description = "message: Denúncia não encontrada com o ID fornecido.",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> obterDenunciaById(@PathVariable("id") Integer id) throws Exception;

    @Operation(summary = "Listar denúncias por ID de usuário", description = "Esse Método Lista denúncias por ID de usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de denúncias filtrada por ID de usuário",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DenunciaDTO.class))),
                    @ApiResponse(responseCode = "400", description = "message: Nenhuma denúncia encontrada para o usuário com ID fornecido.",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<DenunciaDTO>> listByIdUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception;

    @Operation(summary = "Criar denúncia", description = "Esse Método Cria uma nova denúncia")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Denúncia criada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DenunciaCreateDTO.class))),
                    @ApiResponse(responseCode = "400", description = "message: ID Usuário desconhecido.",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @PostMapping("/{idUsuario}")
    public ResponseEntity<DenunciaDTO> criarDenuncia(@PathVariable("idUsuario") Integer idUsuario, @Valid @RequestBody DenunciaCreateDTO denunciaCreateDTO) throws Exception;

    @Operation(summary = "Editar denúncia", description = "Esse Método Edita uma denúncia pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a denúncia atualizada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DenunciaCreateDTO.class))),
                    @ApiResponse(responseCode = "400", description = "message: Denúncia não encontrada com o ID fornecido.",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400 ", description = "message: A denúncia não pertence ao usuário com o ID fornecido.",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @PutMapping("/{idDenuncia}")
    public ResponseEntity<DenunciaDTO>editarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia, @Valid @RequestBody DenunciaCreateDTO denunciaCreateDTO, @PathVariable("idUsuario") Integer idUsuario) throws Exception;

    @Operation(summary = "Deletar denúncia", description = "Esse Método Deleta uma denúncia pelo idDenuncia e idUsuario dono dela.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Denúncia excluída!",
                            content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "400", description = "message: Denúncia não encontrada com o ID fornecido.",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400 ", description = "message: ID Usuário desconhecido.",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @DeleteMapping("/{idDenuncia}")
    public ResponseEntity<Object> deletarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia, @PathVariable("idUsuario") Integer idUsuario) throws Exception;
}

