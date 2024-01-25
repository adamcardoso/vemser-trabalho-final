package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.exceptions.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IDenunciaController {

    @Operation(summary = "Listar todas as denúncias", description = "Lista todas as denúncias")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de denúncias"),
                    @ApiResponse(responseCode = "404", description = "Denúncias não encontradas"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<Response<List<DenunciaDTO>>> listarTodasDenuncias();

    @Operation(summary = "Obter denúncia por ID", description = "Obtém uma denúncia pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a denúncia"),
                    @ApiResponse(responseCode = "404", description = "Denúncia não encontrada"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Response<DenunciaDTO>> obterDenunciaById(@PathVariable("id") Integer id);

    @Operation(summary = "Listar denúncias por ID de usuário", description = "Lista denúncias por ID de usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de denúncias filtrada por ID de usuário"),
                    @ApiResponse(responseCode = "404", description = "Denúncias não encontradas"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Response<List<DenunciaDTO>>> listByIdUsuario(@PathVariable("idUsuario") Integer idUsuario);

    @Operation(summary = "Criar denúncia", description = "Cria uma nova denúncia")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Denúncia criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idUsuario}")
    public ResponseEntity<Response<DenunciaDTO>> criarDenuncia(@PathVariable("idUsuario") Integer idUsuario, @Valid @RequestBody DenunciaCreateDTO denunciaCreateDTO);

    @Operation(summary = "Editar denúncia", description = "Edita uma denúncia pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a denúncia atualizada"),
                    @ApiResponse(responseCode = "404", description = "Denúncia não encontrada"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idDenuncia}")
    public ResponseEntity<Response<DenunciaDTO>> editarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia, @Valid @RequestBody DenunciaCreateDTO denunciaCreateDTO);

    @Operation(summary = "Deletar denúncia", description = "Deleta uma denúncia pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Denúncia deletada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Denúncia não encontrada"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idDenuncia}")
    public ResponseEntity<Response<Object>> deletarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia, @PathVariable("idUsuario") Integer idUsuario);
}

