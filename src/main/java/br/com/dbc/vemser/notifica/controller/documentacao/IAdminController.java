package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.usuario.CreateUsuarioDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UpdateUsuarioDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IAdminController {
    @Operation(summary = "Listar Usuários", description = "Listar Usuários")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o usuário"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/list-usuario")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() throws Exception;

    @Operation(summary = "Listar Admins", description = "Listar Admins")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o usuário"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/list-admin")
    public ResponseEntity<List<UsuarioDTO>> listarAdmins() throws Exception;

    @Operation(summary = "Obter usuário por ID", description = "Obtém um usuário pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o usuário"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obterUsuarioById(@PathVariable("idUsuario") Integer idUsuario)throws Exception;


    @Operation(summary = "Criar Admin", description = "Cria um novo Admin")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuarioAdmin(@Valid @RequestBody CreateUsuarioDTO novoUsuario)throws Exception;

    @Operation(summary = "Atualizar Admin", description = "Atualiza um Admin pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o usuário atualizado"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizarAdmin(@PathVariable("idUsuario") Integer idUsuario, @Valid @RequestBody UpdateUsuarioDTO novoUsuario)throws Exception;

    @Operation(summary = "Remover usuário", description = "Remove um usuário pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> removerUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception;

    @Operation(summary = "Listar todas Denúncias", description = "Lista todas Denúncias")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<List<DenunciaDTO>> listarTodasDenuncias() throws Exception;

    @Operation(summary = "Listar todas Denúncias", description = "Lista todas Denúncias")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idDenuncia}")
    public ResponseEntity<Object> deletarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia) throws Exception;

    @Operation(summary = "Listar todas Denúncias", description = "Lista todas Denúncias")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idDenuncia}")
    public ResponseEntity<DenunciaDTO> obterDenunciaById(@PathVariable("idDenuncia") Integer idDenuncia) throws Exception;
}
