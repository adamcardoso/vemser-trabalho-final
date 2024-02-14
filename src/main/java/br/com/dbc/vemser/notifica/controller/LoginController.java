package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.ILoginController;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.notifica.dto.instituicao.LoginInstituicaoDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioLoginDTO;
import br.com.dbc.vemser.notifica.entity.Instituicao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.security.TokenService;
import br.com.dbc.vemser.notifica.service.LoginService;
import br.com.dbc.vemser.notifica.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
@Tag(name = "Login Controller")
public class LoginController {

    private final TokenService tokenService;
    public final AuthenticationManager authenticationManager;
    public final UsuarioService usuarioService;
    private final LoginService loginService;

    @PostMapping("/instituicao")
    public ResponseEntity<String> loginInstituicao(@RequestBody @Valid LoginInstituicaoDTO loginInstituicaoDTO) throws RegraDeNegocioException {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            loginInstituicaoDTO.getEmailInstituicao(),
                            loginInstituicaoDTO.getSenhaInstituicao()
                    );

            Authentication authentication =
                    authenticationManager.authenticate(
                            usernamePasswordAuthenticationToken);

            Instituicao instituicao = (Instituicao) authentication.getPrincipal();

            return ResponseEntity.ok(tokenService.generateToken(instituicao));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou Senha Incorretos!");
        }
    }

    @PostMapping("/usuario")
    public ResponseEntity<String> login(@RequestBody @Valid UsuarioLoginDTO loginDTO) throws RegraDeNegocioException {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmailUsuario(),
                            loginDTO.getSenhaUsuario()
                    );

            Authentication authentication =
                    authenticationManager.authenticate(
                            usernamePasswordAuthenticationToken);

            Usuario usuarioValidado = (Usuario) authentication.getPrincipal();

            return ResponseEntity.ok(tokenService.generateToken(usuarioValidado));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou Senha Incorretos!");
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        UsuarioDTO usuarioCriado = loginService.createUsuario(usuarioCreateDTO);
        return new ResponseEntity<>(usuarioCriado, HttpStatus.OK);
    }

    @GetMapping("/usuario-logado")
    public ResponseEntity<Usuario> usuarioLogado() throws RegraDeNegocioException {
        Usuario usuario = loginService.getLoggedUser();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
