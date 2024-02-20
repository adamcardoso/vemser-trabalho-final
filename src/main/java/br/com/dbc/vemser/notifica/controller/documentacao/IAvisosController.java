package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.avisos.AvisosCreateDTO;
import br.com.dbc.vemser.notifica.dto.avisos.AvisosDTO;
import br.com.dbc.vemser.notifica.exceptions.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAvisosController {

    @Operation(summary = "Criar um Aviso", description = "Aqui é onde a Intituição Cria um Aviso")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o Aviso"),
                    @ApiResponse(responseCode = "400", description = "Erro",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @PostMapping
    public ResponseEntity<AvisosDTO> createAviso(@RequestBody AvisosCreateDTO avisoDTO) throws Exception;
}
