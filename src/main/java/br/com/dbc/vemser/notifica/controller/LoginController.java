package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.ILoginController;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioLoginDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.LoginRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
@Tag(name = "Login Controller")
public class LoginController implements ILoginController{

    private final TokenService tokenService;
    public final AuthenticationManager authenticationManager;
    public final UsuarioService usuarioService;
    public final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final LoginService loginService;

    @PostMapping
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
    public ResponseEntity<String> cadastrar(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        if (loginService.findByEmailUsuario(usuarioCreateDTO.getEmailUsuario()).isPresent()) {
            throw new RegraDeNegocioException("Insira um Cadastro Válido!");
        }

        String senhaCriptografada = bCryptPasswordEncoder.encode(usuarioCreateDTO.getSenhaUsuario());

        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setNomeUsuario(usuarioCreateDTO.getNomeUsuario());
        usuarioEntity.setEmailUsuario(usuarioCreateDTO.getEmailUsuario());
        usuarioEntity.setNumeroCelular(usuarioCreateDTO.getNumeroCelular());
        usuarioEntity.setSenhaUsuario(senhaCriptografada);
        usuarioEntity.setEtniaUsuario(usuarioCreateDTO.getEtniaUsuario());
        usuarioEntity.setDataNascimento(usuarioCreateDTO.getDataNascimento());
        usuarioEntity.setClasseSocial(usuarioCreateDTO.getClasseSocial());
        usuarioEntity.setGeneroUsuario(usuarioCreateDTO.getGeneroUsuario());
        usuarioEntity.setTipoUsuario(TipoUsuario.COMUM);

        usuarioRepository.save(usuarioEntity);

        return ResponseEntity.ok("Cadastro feito com Sucesso!");
    }


    @GetMapping("/usuario-logado")
    public ResponseEntity<Usuario> usuarioLogado() throws RegraDeNegocioException {
        Usuario usuario = loginService.getLoggedUser();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}


