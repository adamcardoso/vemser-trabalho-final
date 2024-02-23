package br.com.dbc.vemser.notifica.controller.documentacao;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.instituicao.InstitucaoCreateDTO;
import br.com.dbc.vemser.notifica.dto.instituicao.InstituicaoDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.dto.usuario.admin_dto.DenunciaListDTO;
import br.com.dbc.vemser.notifica.dto.usuario.admin_dto.UsuarioListDTO;
import br.com.dbc.vemser.notifica.exceptions.ErrorResponse;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
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
    @Operation(summary = "Listar Meu usuario", description = "Lista todos os dados do meu usuario")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do usuario"),

            }
    )
    @GetMapping("/meu-usuario")
    public ResponseEntity<UsuarioDTO> usuario() throws RegraDeNegocioException;

    @Operation(summary = "Listar todos Usuários", description = "Listar todos os usuario")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os usuários"),
            }
    )
    @GetMapping("/listar-usuarios")
    public ResponseEntity<List<UsuarioListDTO>> listAll();

    @Operation(summary = "Listar Admins", description = "Listar Admins")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os usuários"),
            }
    )
    @GetMapping("/list-admins")
    public ResponseEntity<List<UsuarioListDTO>> listarAdmins();

    @Operation(summary = "Obter usuário por ID", description = "Obtém um usuário pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os usuários"),
                    @ApiResponse(responseCode = "400", description = "Usuario não encontrado!",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
            }
    )
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obterUsuarioById(@PathVariable("idUsuario") Integer idUsuario) throws RegraDeNegocioException;
    @Operation(summary = "Criar Admin", description = "Cria um novo Admin")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Object: não deve ser nulo",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
            }
    )
    @PostMapping("/criar-admin")

    public ResponseEntity<UsuarioDTO> criarUsuarioAdmin(@Valid @RequestBody UsuarioCreateDTO novoUsuario);
    @Operation(summary = "Criar instituição", description = "Criar uma instituição")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Instituição criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Object: não deve ser nulo",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
            }
    )
    @PostMapping("/criar-instituicao")
    public ResponseEntity<InstituicaoDTO> criarInstituicao(@Valid @RequestBody InstitucaoCreateDTO novaInstituicao) throws RegraDeNegocioException;

    @Operation(summary = "atualizar usuário", description = "atualizar seu usuario admin")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Admin atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Object: não deve ser nulo",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class))),
            }
    )
    @PutMapping("/editar-usuario")
    public ResponseEntity<UsuarioDTO> atualizarAdmin( @Valid @RequestBody UsuarioUpdateDTO novoUsuario) throws RegraDeNegocioException;

    @Operation(summary = "editarSenha", description = "editar sua senha")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna token"),
                    @ApiResponse(responseCode = "400", description = "senha incorreta",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @PutMapping("/editar-senha")
    public ResponseEntity<String> editarSenha(@RequestParam(required = false) Integer idUsuario, @RequestParam String senhaAtual, @RequestParam String novaSenha) throws RegraDeNegocioException;

    @Operation(summary = "Obter Denúncia", description = "Obter denuncia por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "denuncia retornada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Denuncia não encontrado!",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/denuncia/{idDenuncia}")
    public ResponseEntity<DenunciaDTO> obterDenunciaById(@PathVariable("idDenuncia") Integer idDenuncia) throws RegraDeNegocioException;

    @Operation(summary = "Lista denuncia", description = "Lista denuncias ativas")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "retorna denuncias"),
            }
    )
    @GetMapping("/list-denuncias-ativas")
    public ResponseEntity<List<DenunciaListDTO>> listarTodasDenunciasAtivas();

    @Operation(summary = "deletar denuncia", description = "deletar denuncia por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "denuncia removido com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Denuncia não encontrado!",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @DeleteMapping("/denuncia/{idDenuncia}")
    public ResponseEntity<Object> deletarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia) throws RegraDeNegocioException;

    @Operation(summary = "deletar comentario", description = "deletar comentario por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "comentario removido com sucesso"),
                    @ApiResponse(responseCode = "400", description = "comentario não encontrado!",
                            content = @Content(mediaType = "application/json", schema = @Schema(hidden = false, implementation = ErrorResponse.class)))
            }
    )
    @DeleteMapping
    public ResponseEntity<Void> deletarComentario(@RequestParam Integer idComentario);
}
