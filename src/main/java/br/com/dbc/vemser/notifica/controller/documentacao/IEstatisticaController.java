package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.estatistica.EstatisticaDto;
import br.com.dbc.vemser.notifica.exceptions.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

public interface IEstatisticaController {

    @Operation(summary = "Obter estatística por coluna", description = "Obtém estatísticas por coluna")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna as estatísticas por coluna"),
                    @ApiResponse(responseCode = "404", description = "Estatísticas não encontradas"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/bycolumn")
    public ResponseEntity<Response<HashMap<String, List<EstatisticaDto>>>> obterEstatistica(@RequestParam("coluna") List<String> colunas);
}
