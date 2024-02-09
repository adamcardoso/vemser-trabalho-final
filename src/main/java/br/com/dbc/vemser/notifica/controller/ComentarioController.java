package br.com.dbc.vemser.notifica.controller;


import br.com.dbc.vemser.notifica.controller.documentacao.IComentarioController;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioUpdateDTO;
import br.com.dbc.vemser.notifica.service.ComentarioService;
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

    @PostMapping
    public ResponseEntity<ComentarioDTO> adicionarComentario(@Valid @RequestBody ComentarioCreateDTO comentarioDto){
        return new ResponseEntity<>(comentarioService.criarComentario(comentarioDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> atualizarComentario(@PathVariable("id") Integer id, @Valid @RequestBody ComentarioUpdateDTO comentarioDto) throws Exception{
        return new ResponseEntity<>(comentarioService.editarComentario(id, comentarioDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable("id") Integer id) throws Exception {
        comentarioService.deletarComentario(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
