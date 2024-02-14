package br.com.dbc.vemser.notifica.controller;


import br.com.dbc.vemser.notifica.controller.documentacao.IComentarioController;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioUpdateDTO;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.service.ComentarioService;
import br.com.dbc.vemser.notifica.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "Comentário Controller")
@RequestMapping("/comentario")
public class ComentarioController {
    private final ComentarioService comentarioService;
    private final LoginService loginService;

    @PostMapping("/{idDenuncia}/denuncia")
    public ResponseEntity<ComentarioDTO> adicionarComentario(@PathVariable("idDenuncia") Integer idDenuncia,
                                                             @RequestBody String comentario) throws RegraDeNegocioException {
        return new ResponseEntity<>(comentarioService.criarComentario(loginService.getIdLoggedUser(), idDenuncia, comentario), HttpStatus.CREATED);
    }

    @PutMapping("/{idComentario}")
    public ResponseEntity<ComentarioDTO> atualizarComentario(@PathVariable("idComentario") Integer idComentario, @RequestBody String comentario) throws Exception{
        return new ResponseEntity<>(comentarioService.editarComentario(loginService.getIdLoggedUser(), idComentario, comentario), HttpStatus.OK);
    }

    @DeleteMapping("/{idComentario}")
    public ResponseEntity<Void> deletarComentario(@PathVariable("idComentario") Integer idComentario) throws RegraDeNegocioException {
        comentarioService.deletarComentario(loginService.getIdLoggedUser(), idComentario);
        return ResponseEntity.ok().build();
    }
}
