package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IAdminController;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.service.UsuarioAdminService;
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
public class AdminController implements IAdminController {
    private final UsuarioAdminService usuarioAdminService;

    @GetMapping("/list-usuario")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() throws Exception {
        List<UsuarioDTO> usuarios = usuarioAdminService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/list-admin")
    public ResponseEntity<List<UsuarioDTO>> listarAdmins() throws Exception {
        List<UsuarioDTO> usuarios = usuarioAdminService.listarAdmins();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obterUsuarioById(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        UsuarioDTO usuario = usuarioAdminService.obterUsuarioById(idUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuarioAdmin(@Valid @RequestBody UsuarioCreateDTO novoUsuario) throws Exception {
        UsuarioDTO usuarios = usuarioAdminService.criarUsuarioAdmin(novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.CREATED);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizarAdmin(@PathVariable("idUsuario") Integer idUsuario, @Valid @RequestBody UsuarioUpdateDTO novoUsuario) throws Exception {
        UsuarioDTO usuarios = usuarioAdminService.atualizarAdmin(idUsuario, novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @DeleteMapping("/usuario/{idUsuario}")
    public ResponseEntity<Object> removerUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        String deleted = usuarioAdminService.removerUsuario(idUsuario);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/list-denuncias")
    public ResponseEntity<List<DenunciaDTO>> listarTodasDenuncias() throws Exception {
        List<DenunciaDTO> denunciaDTOS = usuarioAdminService.listarTodasDenuncias();
        return ResponseEntity.ok(denunciaDTOS);
    }

    @DeleteMapping("/denuncia/{idDenuncia}")
    public ResponseEntity<Object> deletarDenuncia(@PathVariable Integer idDenuncia) throws Exception {
        String deleted = usuarioAdminService.deletarDenuncia(idDenuncia);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/denuncia/{idDenuncia}")
    public ResponseEntity<DenunciaDTO> obterDenunciaById(@PathVariable("idDenuncia") Integer idDenuncia) throws Exception {
        DenunciaDTO denunciaDTO = usuarioAdminService.obterDenunciaById(idDenuncia);
        return ResponseEntity.ok(denunciaDTO);
    }
}