package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IAdminController;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.instituicao.InstitucaoCreateDTO;
import br.com.dbc.vemser.notifica.dto.instituicao.InstituicaoDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.admin_dto.DenunciaListDTO;
import br.com.dbc.vemser.notifica.dto.usuario.admin_dto.UsuarioListDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.service.AdminService;
import br.com.dbc.vemser.notifica.service.CreateRegistrosService;
import br.com.dbc.vemser.notifica.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Validated
@AllArgsConstructor
@Tag(name = "Admin Controller")
public class AdminController implements IAdminController {
    private final AdminService adminService;
    private final LoginService loginService;
    private final CreateRegistrosService createRegistrosService;


    @GetMapping("/meu-usuario")
    public ResponseEntity<UsuarioDTO> usuario() throws RegraDeNegocioException {
        UsuarioDTO usuario = adminService.obterUsuarioById(loginService.getIdLoggedUser());
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/listar-usuarios")
    public ResponseEntity<List<UsuarioListDTO>> listAll() {
        List<UsuarioListDTO> usuarios = adminService.listAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/list-admins")
    public ResponseEntity<List<UsuarioListDTO>> listarAdmins() {
        List<UsuarioListDTO> usuarios = adminService.listUsuariosAdmin();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obterUsuarioById(@PathVariable("idUsuario") Integer idUsuario) throws RegraDeNegocioException {
        UsuarioDTO usuario = adminService.obterUsuarioById(idUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/criar-admin")
    public ResponseEntity<UsuarioDTO> criarUsuarioAdmin(@Valid @RequestBody UsuarioCreateDTO novoUsuario) {
        UsuarioDTO usuarios = adminService.criarUsuarioAdmin(novoUsuario);
        createRegistrosService.insertCreateRegistro();
        return new ResponseEntity<>(usuarios, HttpStatus.CREATED);
    }

    @PostMapping("/criar-instituicao")
    public ResponseEntity<InstituicaoDTO> criarInstituicao(@Valid @RequestBody InstitucaoCreateDTO novaInstituicao) throws RegraDeNegocioException {
        InstituicaoDTO instituicao = adminService.criarUsuarioInstitucao(novaInstituicao);
        return new ResponseEntity<>(instituicao, HttpStatus.CREATED);
    }

    @PutMapping("/editar-usuario")
    public ResponseEntity<UsuarioDTO> atualizarAdmin( @Valid @RequestBody UsuarioUpdateDTO novoUsuario) throws RegraDeNegocioException {
        UsuarioDTO usuarios = adminService.atualizarUsuario(loginService.getIdLoggedUser(), novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping("/editar-senha")
    public ResponseEntity<String> editarSenha(@RequestParam(required = false) Integer idUsuario,
                                              @RequestParam String senhaAtual, @RequestParam String novaSenha) throws RegraDeNegocioException {
        if (idUsuario == null){
            idUsuario = loginService.getIdLoggedUser();
        }
        String token = adminService.attSenha(idUsuario, senhaAtual, novaSenha);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @DeleteMapping("/usuario/{idUsuario}")
    public ResponseEntity<Object> removerUsuario(@PathVariable("idUsuario") Integer idUsuario) {
        adminService.removerUsuario(idUsuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/denuncia/{idDenuncia}")
    public ResponseEntity<DenunciaDTO> obterDenunciaById(@PathVariable("idDenuncia") Integer idDenuncia) throws RegraDeNegocioException {
        DenunciaDTO denunciaDTO = adminService.denunciaById(idDenuncia);
        return ResponseEntity.ok(denunciaDTO);
    }

    @GetMapping("/list-denuncias-ativas")
    public ResponseEntity<List<DenunciaListDTO>> listarTodasDenunciasAtivas(){
        List<DenunciaListDTO> denunciaDTOS = adminService.listarTodasDenunciasAtivas() ;
        return ResponseEntity.ok(denunciaDTOS);
    }

    @DeleteMapping("/denuncia/{idDenuncia}")
    public ResponseEntity<Object> deletarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia) throws RegraDeNegocioException {
        adminService.deletarDenuncia(idDenuncia);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarComentario(@RequestParam Integer idComentario){
        adminService.deletarComentario(idComentario);
        return ResponseEntity.ok().build();
    }
}