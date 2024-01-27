package br.com.dbc.vemser.notifica.controller;


import br.com.dbc.vemser.notifica.controller.documentacao.IComentarioController;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioUpdateDTO;
import br.com.dbc.vemser.notifica.exceptions.Response;
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
    public ResponseEntity<Response<ComentarioDTO>> obterComentarioById(@PathVariable("id") Integer id){
        try{
            return comentarioService.obterComentarioById(id)
                    .map(c -> new ResponseEntity<>(new Response<>(c, 200, "Comentário obtido com sucesso!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 404, "Comentário não encontrado"), HttpStatus.NOT_FOUND));
        } catch (Exception e){
            return new ResponseEntity<>(
                    new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/denuncia")
    public ResponseEntity<Response<List<ComentarioDTO>>> listarComentariosByIdDenuncia(@PathVariable("id") Integer id){
        try{
            return comentarioService.listarComentariosByIdDenuncia(id)
                    .map(c -> new ResponseEntity<>(new Response<>(c, 200, "Comentários obtidos com sucesso!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 404, "Denúncia não encontrada!"), HttpStatus.NOT_FOUND));
        } catch (Exception e){
            return new ResponseEntity<>(
                new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Response<ComentarioDTO>> adicionarComentario(@RequestBody ComentarioCreateDTO comentarioDto) {
        try{
            return comentarioService.criarComentario(comentarioDto)
                    .map(c -> new ResponseEntity<>(new Response<>(c, 201, "Comentário criado com sucesso!"), HttpStatus.CREATED))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 400, "Ocorreu um problema!"), HttpStatus.BAD_REQUEST));
        } catch(Exception e){
            return new ResponseEntity<>(new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<ComentarioDTO>> atualizarComentario(@PathVariable("id") Integer id, @RequestBody ComentarioUpdateDTO comentarioDto){
        try{
            return comentarioService.editarComentario(id, comentarioDto)
                    .map(c -> new ResponseEntity<>(new Response<>(c, 200, "Comentário atualizado com sucesso!"), HttpStatus.CREATED))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 404, "Comentário não encontrado!"), HttpStatus.NOT_FOUND));
        } catch(Exception e){
            return new ResponseEntity<>(new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> deletarComentario(@PathVariable("id") Integer id) {
        try{
            return comentarioService.deletarComentario(id)
                    .map(c -> new ResponseEntity<>(new Response<>(null, 200, "Comentário deletado com sucesso!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 404, "Comentário não encontrado!"), HttpStatus.NOT_FOUND));
        } catch(Exception e){
            return new ResponseEntity<>(new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
