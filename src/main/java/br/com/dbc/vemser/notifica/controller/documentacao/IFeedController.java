package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.feed.FeedDenunciasDto;
import br.com.dbc.vemser.notifica.exceptions.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IFeedController {
    @Operation(summary = "Listar todas as denúncias", description = "Esse Método é o Feed que Lista todas as denúncias sem os comentários")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de denúncias",
                            content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @GetMapping
    ResponseEntity<Page<FeedDenunciasDto>> listarTodasDenuncias(@PageableDefault(size = 10, page = 10)  Pageable pageable) throws Exception;

    @Operation(summary = "Listar todas as denúncias com comentários", description = "Esse Método é o Feed que Lista todas as denúncias com os comentários")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de denúncias",
                            content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @GetMapping("/denuncias-com-comentarios")
    ResponseEntity<Page<FeedDenunciasDto>> listarTodasDenunciasComComentarios(@PageableDefault(size = 10, page = 0) Pageable pageable) throws Exception;

    @Operation(summary = "Obter denúncia por ID", description = "Esse Método obtém uma denúncia pelo id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a denúncia",
                            content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "400", description = "Denúncia não encontrada!",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
            }
    )
    @GetMapping("/{idDenuncia}/com-comentarios")
    ResponseEntity<DenunciaDTO> listarDenunciaComComentarios(@PathVariable("idDenuncia") Integer idDenuncia) throws Exception;
}
