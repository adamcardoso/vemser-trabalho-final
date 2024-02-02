package br.com.dbc.vemser.notifica.controller;


import br.com.dbc.vemser.notifica.controller.documentacao.IComentarioController;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioUpdateDTO;
import br.com.dbc.vemser.notifica.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comentario")
public class ComentarioController implements IComentarioController {
    private final ComentarioService comentarioService;

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> obterComentarioById(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(comentarioService.obterComentarioById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/denuncia")
    public ResponseEntity<List<ComentarioDTO>> listarComentariosByIdDenuncia(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(comentarioService.listarComentariosByIdDenuncia(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> adicionarComentario(@RequestBody ComentarioCreateDTO comentarioDto) throws Exception{
        return new ResponseEntity<>(comentarioService.criarComentario(comentarioDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> atualizarComentario(@PathVariable("id") Integer id, @RequestBody ComentarioUpdateDTO comentarioDto) throws Exception{
        return new ResponseEntity<>(comentarioService.editarComentario(id, comentarioDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable("id") Integer id) throws Exception {
        comentarioService.deletarComentario(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
