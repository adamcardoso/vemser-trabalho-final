package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioPerfilDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.service.LoginService;
import br.com.dbc.vemser.notifica.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@Validated
@AllArgsConstructor
@Tag(name = "Usuário Controller")
public class UsuarioController  {
    private final UsuarioService usuarioService;
    private final LoginService loginService;

    @GetMapping("/meu-perfil")
    public ResponseEntity<UsuarioPerfilDTO> obterUsuarioById() throws Exception {
        UsuarioPerfilDTO usuario = usuarioService.obterUsuarioById(loginService.getIdLoggedUser());
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/meus-comentarios")
    public ResponseEntity<List<ComentarioDTO>> obterComentariosByIdUsuario() throws Exception {
        List<ComentarioDTO> comentarios = usuarioService.listComentario(loginService.getIdLoggedUser());
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }

    @GetMapping("/minhas-denuncias")
    public ResponseEntity<List<DenunciaDTO>> obterDenunciaByIdUsuario() throws Exception {
        List<DenunciaDTO> denuncia = usuarioService.listDenuncia(loginService.getIdLoggedUser());
        return new ResponseEntity<>(denuncia, HttpStatus.OK);
    }

    @PutMapping("/atualizar-perfil")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@Valid @RequestBody UsuarioUpdateDTO novoUsuario) throws Exception {
        UsuarioDTO usuarios = usuarioService.atualizarUsuario(loginService.getIdLoggedUser(), novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping("/atualizar-senha")
    public ResponseEntity<String> atualizarSenha(@RequestParam String senhaAtual, @RequestParam String novaSenha) throws RegraDeNegocioException {
        String token = usuarioService.attSenha(loginService.getIdLoggedUser(), senhaAtual, novaSenha);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @DeleteMapping("/desativar-perfil")
    public ResponseEntity<Void> removerUsuario() throws Exception {
        usuarioService.removerUsuario(loginService.getIdLoggedUser());
        return ResponseEntity.noContent().build();
    }
}