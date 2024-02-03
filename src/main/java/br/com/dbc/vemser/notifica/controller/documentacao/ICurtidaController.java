package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.curtida.CurtidaDto;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.exceptions.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICurtidaController {
    @Operation(summary = "Adicionar curtida", description = "Método adiciona e remove curtida de comentário.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna void",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CurtidaDto.class))),
                    @ApiResponse(responseCode = "400", description = "Usuário não encontrado!.",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Comentário não encontrado!",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @PostMapping
    void apoiar(@RequestBody CurtidaDto curtidaDto) throws Exception;
}
