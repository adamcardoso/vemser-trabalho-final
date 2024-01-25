package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IDenunciaController;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDto;
import br.com.dbc.vemser.notifica.dto.comentario.CreateComentarioDto;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.entity.Response;
import br.com.dbc.vemser.notifica.service.DenunciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/denuncia")
public class DenunciaController implements IDenunciaController {

    private final DenunciaService denunciaService;

    @GetMapping
    public ResponseEntity<Response<List<DenunciaDTO>>> listarTodasDenuncias() {
        try {
            return denunciaService.listarTodasDenuncias()
                    .map(denuncias -> new ResponseEntity<>(new Response<>(denuncias, 200, "Todas as denúncias obtidas com sucesso!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 404, "Denúncias não encontradas!"), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<DenunciaDTO>> obterDenunciaById(@PathVariable("id") Integer id) {
        try {
            return denunciaService.obterDenunciaById(id)
                    .map(d -> new ResponseEntity<>(new Response<>(d, 200, "Denúncia obtida com sucesso!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 404, "Denúncia não encontrada!"), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Response<List<DenunciaDTO>>> listByIdUsuario(@PathVariable("idUsuario") Integer idUsuario) {
        try {
            return denunciaService.listByIdUsuario(idUsuario)
                    .map(denuncias -> new ResponseEntity<>(new Response<>(denuncias, 200, "Denúncias obtidas com sucesso!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 404, "Denúncias não encontradas!"), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<Response<DenunciaDTO>> criarDenuncia(@PathVariable("idUsuario") Integer idUsuario, @RequestBody DenunciaCreateDTO denunciaCreateDTO) {
        try {
            return denunciaService.criarDenuncia(denunciaCreateDTO, idUsuario)
                    .map(d-> new ResponseEntity<>(new Response<>(d, 201, "Denúncia criada com sucesso!"), HttpStatus.CREATED))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 400, "Ocorreu um problema ao criar a denúncia!"), HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idDenuncia}")
    public ResponseEntity<Response<DenunciaDTO>> editarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia, @RequestBody DenunciaCreateDTO denunciaCreateDTO) {
        try {
            return denunciaService.editarDenuncia(denunciaCreateDTO, idDenuncia)
                    .map(d -> new ResponseEntity<>(new Response<>(d, 200, "Denúncia editada com sucesso!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 404, "Denúncia não encontrada!"), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{idDenuncia}")
    public ResponseEntity<Response<Object>> deletarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia) {
        try {
            return denunciaService.deletarDenuncia(idDenuncia)
                    .map(d -> new ResponseEntity<>(new Response<>(null, 200, "Denúncia deletada com sucesso!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 404, "Denúncia não encontrada!"), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
