package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.ILoginController;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioLoginDTO;
import br.com.dbc.vemser.notifica.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController implements ILoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        try {
            UsuarioDTO usuario = loginService.autenticarUsuario(usuarioLoginDTO.getEmailUsuario(), usuarioLoginDTO.getSenhaUsuario());
            return ResponseEntity.ok().body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciais inválidas, Usuário ou Senha Incorretos!");
        }
    }
}

