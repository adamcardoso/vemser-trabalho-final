package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IUsuarioController;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
@Validated
@AllArgsConstructor
@Tag(name = "Usuário Controller")
public class UsuarioController  {
    private final UsuarioService usuarioService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obterUsuarioById(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        UsuarioDTO usuario = usuarioService.obterUsuarioById(idUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioCreateDTO novoUsuario){
        UsuarioDTO usuarios = usuarioService.criarUsuario(novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable("idUsuario") Integer idUsuario,@Valid @RequestBody UsuarioUpdateDTO novoUsuario) throws Exception {
        UsuarioDTO usuarios = usuarioService.atualizarUsuario(idUsuario, novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> removerUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        usuarioService.removerUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }
}