package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.ILoginController;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioLoginDTO;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
@Tag(name = "Login Controller")
@CrossOrigin(origins = "*")
public class LoginController implements ILoginController{

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<Object> login(@Valid @RequestBody UsuarioLoginDTO usuarioLoginDTO) throws RegraDeNegocioException {
        UsuarioDTO usuario = loginService.autenticarUsuario(usuarioLoginDTO.getEmailUsuario(), usuarioLoginDTO.getSenhaUsuario());
        return ResponseEntity.ok().body(usuario);
    }
}


