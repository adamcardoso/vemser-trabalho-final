package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.dto.usuario.CreateUsuarioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UpdateUsuarioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDto;
import br.com.dbc.vemser.notifica.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
@Validated
public class UsuarioController {
    private UsuarioService usuarioService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> obterUsuarioById(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        UsuarioDto usuario = usuarioService.obterUsuarioById(idUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() throws Exception {
        List<UsuarioDto> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> criarUsuario(@Valid @RequestBody CreateUsuarioDto novoUsuario) throws Exception {
        UsuarioDto usuarios = usuarioService.criarUsuario(novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable("idUsuario") Integer idUsuario, @Valid @RequestBody UpdateUsuarioDto novoUsuario) throws Exception {
        UsuarioDto usuarios = usuarioService.atualizarUsuario(idUsuario, novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> removerUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        usuarioService.removerUsuario(idUsuario);
        return ResponseEntity.ok().build();
    }
}
