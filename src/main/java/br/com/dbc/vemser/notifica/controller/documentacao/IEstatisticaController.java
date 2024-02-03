package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.estatistica.EstatisticaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IEstatisticaController {
    @Operation(
            summary = "Obter estatísticas",
            description = "Esse Método Obtém as Estatísticas pelos Dados dos Usuários que Criaram Denúncias."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Retorna as estatísticas por coluna",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstatisticaDTO.class))
    )
    @GetMapping
    public ResponseEntity<EstatisticaDTO> obterEstatisticas();
}

