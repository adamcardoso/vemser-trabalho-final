package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.exceptions.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IAdminController {
    @Operation(summary = "Listar Usuários Ativos", description = "Listar Usuários Ativos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os usuários"),
                    @ApiResponse(responseCode = "400", description = "Usuario não encontrado!",
                    content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/list-usuario-ativos")
    public ResponseEntity<Page<UsuarioDTO>> listarUsuariosPaginadosAtivos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "Listar Usuários Inativos", description = "Listar Usuários Inativo")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os usuários"),
                    @ApiResponse(responseCode = "400", description = "Usuario não encontrado!",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/list-usuario-inativos")
    public ResponseEntity<Page<UsuarioDTO>> listarUsuariosPaginadosInativos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "Listar Admins", description = "Listar Admins")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os usuários"),
            }
    )
    @GetMapping("/list-admin")
    public ResponseEntity<List<UsuarioDTO>> listarAdmins() throws Exception;

    @Operation(summary = "Obter usuário por ID", description = "Obtém um usuário pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os usuários"),
                    @ApiResponse(responseCode = "400", description = "Usuario não encontrado!",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
            }
    )
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obterUsuarioById(@PathVariable("idUsuario") Integer idUsuario)throws Exception;


    @Operation(summary = "Criar Admin", description = "Cria um novo Admin")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Object: não deve ser nulo",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
            }
    )
    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuarioAdmin(@Valid @RequestBody UsuarioCreateDTO novoUsuario)throws Exception;

    @Operation(summary = "Atualizar Admin", description = "Atualiza um Admin pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Usuário não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
            }
    )
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizarAdmin(@PathVariable("idUsuario") Integer idUsuario, @Valid @RequestBody UsuarioUpdateDTO novoUsuario)throws Exception;

    @Operation(summary = "Remover usuário", description = "Remove um usuário pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Usuário não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> removerUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception;

    @Operation(summary = "Listar todas Denúncias Ativas", description = "Lista todas Denúncias Ativas")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna as Denuncias Ativas"),
                    @ApiResponse(responseCode = "400", description = "Denuncias não encontradas",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/list-denuncias-ativas")
    public ResponseEntity<List<DenunciaDTO>> listarTodasDenunciasAtivas() throws Exception;

    @Operation(summary = "Deletar Denúncia", description = "Deleta denuncia por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "denuncia removido com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Denuncia não encontrado!",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @DeleteMapping("/{idDenuncia}")
    public ResponseEntity<Object> deletarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia) throws Exception;

    @Operation(summary = "Lista denuncia", description = "Lista denuncia por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "denuncia removido com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Denuncia não encontrado!",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/{idDenuncia}")
    public ResponseEntity<DenunciaDTO> obterDenunciaById(@PathVariable("idDenuncia") Integer idDenuncia) throws Exception;
}
