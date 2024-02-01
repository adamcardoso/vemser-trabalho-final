package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface IFeedController {
    @Operation(summary = "Listar todas as denúncias", description = "Esse Método Lista todas as denúncias")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de denúncias",
                            content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @GetMapping
    public ResponseEntity<List<DenunciaDTO>> listarTodasDenuncias() throws Exception;
}
