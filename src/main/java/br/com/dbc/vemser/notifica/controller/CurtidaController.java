package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.service.CurtidaService;
import br.com.dbc.vemser.notifica.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apoiar")
public class CurtidaController {
    private final CurtidaService curtidaService;
    private final LoginService loginService;


    @PostMapping("/{idComentario}/like-comentario")
    public ResponseEntity<String> apoiarComentario(@PathVariable("idComentario") Integer idComentario) throws Exception {
        return new ResponseEntity<>(curtidaService.apoiarComentario(loginService.getIdLoggedUser(), idComentario), HttpStatus.OK);
    }

    @PostMapping("/{idDenuncia}/like-denuncia")
    public ResponseEntity<String> apoiarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia) throws Exception {
        return new ResponseEntity<>(curtidaService.apoiarDenuncia(loginService.getIdLoggedUser(), idDenuncia), HttpStatus.OK);
    }
}
