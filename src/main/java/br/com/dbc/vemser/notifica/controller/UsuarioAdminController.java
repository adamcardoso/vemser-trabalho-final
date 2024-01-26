package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IUsuarioAdminController;
import br.com.dbc.vemser.notifica.dto.usuario.CreateUsuarioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UpdateUsuarioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDto;
import br.com.dbc.vemser.notifica.service.UsuarioAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/usuario")
@Validated
@AllArgsConstructor
public class UsuarioAdminController implements IUsuarioAdminController {
    private final UsuarioAdminService usuarioAdminService;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() throws Exception {
        List<UsuarioDto> usuarios = usuarioAdminService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> obterUsuarioById(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        UsuarioDto usuario = usuarioAdminService.obterUsuarioById(idUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> criarUsuario(@Valid @RequestBody CreateUsuarioDto novoUsuario) throws Exception {
        UsuarioDto usuarios = usuarioAdminService.criarUsuarioAdmin(novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.CREATED);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable("idUsuario") Integer idUsuario, @Valid @RequestBody UpdateUsuarioDto novoUsuario) throws Exception {
        UsuarioDto usuarios = usuarioAdminService.atualizarUsuario(idUsuario, novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> removerUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        usuarioAdminService.removerUsuario(idUsuario);
        return ResponseEntity.ok().build();
    }
}