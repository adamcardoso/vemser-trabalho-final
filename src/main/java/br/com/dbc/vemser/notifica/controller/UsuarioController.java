package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.service.LoginService;
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
    private final LoginService loginService;

    @GetMapping("/meu-perfil")
    public ResponseEntity<UsuarioDTO> obterUsuarioById() throws Exception {
        UsuarioDTO usuario = usuarioService.obterUsuarioById(loginService.getIdLoggedUser());
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping("/atualizar-perfil")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@Valid @RequestBody UsuarioUpdateDTO novoUsuario) throws Exception {
        UsuarioDTO usuarios = usuarioService.atualizarUsuario(loginService.getIdLoggedUser(), novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @DeleteMapping("/desativar-perfil")
    public ResponseEntity<Void> removerUsuario() throws Exception {
        usuarioService.removerUsuario(loginService.getIdLoggedUser());
        return ResponseEntity.noContent().build();
    }
}