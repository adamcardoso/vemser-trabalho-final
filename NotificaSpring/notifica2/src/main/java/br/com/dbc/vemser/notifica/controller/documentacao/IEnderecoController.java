package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.endereco.CreateEnderecoDto;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDto;
import br.com.dbc.vemser.notifica.dto.endereco.UpdateEnderecoDto;
import br.com.dbc.vemser.notifica.entity.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IEnderecoController {

    @Operation(summary = "Obter endereço por ID", description = "Obtém um endereço pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o endereço"),
                    @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Response<EnderecoDto>> obterEnderecoById(@PathVariable Integer id);

    @Operation(summary = "Listar endereços por ID de usuário", description = "Lista endereços por ID de usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de endereços filtrada por ID de usuário"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}/usuario")
    public ResponseEntity<Response<List<EnderecoDto>>> obterEnderecosByIdUsuario(@PathVariable("id") Integer id);

    @Operation(summary = "Adicionar endereço", description = "Adiciona um novo endereço")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<Response<EnderecoDto>> adicionarEndereco(@Valid @RequestBody CreateEnderecoDto enderecoDto);

    @Operation(summary = "Atualizar endereço", description = "Atualiza um endereço pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o endereço atualizado"),
                    @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Response<EnderecoDto>> atualizarEndereco(@PathVariable("id") Integer id, @Valid @RequestBody UpdateEnderecoDto enderecoDto);

    @Operation(summary = "Remover endereço", description = "Remove um endereço pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> removerEndereco(@PathVariable("id") Integer id);
}
