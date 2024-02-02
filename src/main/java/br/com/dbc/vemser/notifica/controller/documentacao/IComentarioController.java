package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioUpdateDTO;
import br.com.dbc.vemser.notifica.exceptions.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IComentarioController {

    @Operation(summary = "Obter comentário por ID", description = "Obtém um comentário pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o comentário"),
                    @ApiResponse(responseCode = "404", description = "Comentário não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> obterComentarioById(@PathVariable("id") Integer id) throws Exception;

    @Operation(summary = "Listar comentários por ID de denúncia", description = "Lista comentários por ID de denúncia")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de comentários filtrada por ID de denúncia"),
                    @ApiResponse(responseCode = "404", description = "Denúncia não encontrada"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}/denuncia")
    public ResponseEntity<List<ComentarioDTO>> listarComentariosByIdDenuncia(@PathVariable("id") Integer id) throws Exception;

    @Operation(summary = "Adicionar comentário", description = "Adiciona um novo comentário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Comentário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<ComentarioDTO> adicionarComentario(@Valid @RequestBody ComentarioCreateDTO comentarioDto) throws Exception;

    @Operation(summary = "Atualizar comentário", description = "Atualiza um comentário pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o comentário atualizado"),
                    @ApiResponse(responseCode = "404", description = "Comentário não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> atualizarComentario(@PathVariable("id") Integer id, @Valid @RequestBody ComentarioUpdateDTO comentarioDto) throws Exception;

    @Operation(summary = "Deletar comentário", description = "Deleta um comentário pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Comentário deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Comentário não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable("id") Integer id) throws Exception;
}
