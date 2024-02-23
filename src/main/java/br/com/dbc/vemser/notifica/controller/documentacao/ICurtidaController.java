package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.exceptions.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface ICurtidaController {
    @Operation(summary = "Adicionar e remover curtida", description = "Método adiciona e remove curtida de comentário.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna like/dislike"),
                    @ApiResponse(responseCode = "400", description = "Comentário não encontrado!",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @PostMapping("/{idComentario}/like-comentario")
    public ResponseEntity<String> apoiarComentario(@PathVariable("idComentario") Integer idComentario) throws Exception;

    @Operation(summary = "Adicionar e remover curtida", description = "Método adiciona e remove curtida de comentário.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna like/dislike"),
                    @ApiResponse(responseCode = "400", description = "Comentário não encontrado!",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @PostMapping("/{idDenuncia}/like-denuncia")
    public ResponseEntity<String> apoiarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia) throws Exception;

}
