//package br.com.dbc.vemser.notifica.controller.documentacao;
//
//import br.com.dbc.vemser.notifica.exceptions.ErrorResponse;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//public interface ICurtidaController {
//    @Operation(summary = "Adicionar e remover curtida", description = "Método adiciona e remove curtida de comentário.")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(responseCode = "200", description = "Retorna vazio",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CurtidaDto.class))),
//                    @ApiResponse(responseCode = "400", description = "Usuário não encontrado!.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
//                    @ApiResponse(responseCode = "400", description = "Comentário não encontrado!",
//                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
//            }
//    )
//    @PostMapping
//    void apoiarComentario(@RequestBody CurtidaDto curtidaDto) throws Exception;
//
//    @Operation(summary = "Adicionar e remover curtida", description = "Método adiciona e remove curtida de denúncia.")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(responseCode = "200", description = "Retorna vazio",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CurtidaDto.class))),
//                    @ApiResponse(responseCode = "400", description = "Usuário não encontrado!.",
//                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
//                    @ApiResponse(responseCode = "400", description = "Denúncia não encontrada!",
//                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
//            }
//    )
//    @PostMapping
//    void apoiarDenuncia(@RequestBody CurtidaDto curtidaDto) throws Exception;
//}
