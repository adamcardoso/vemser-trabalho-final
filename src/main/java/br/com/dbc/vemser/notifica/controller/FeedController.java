package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IFeedController;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.feed.FeedDenunciasDto;
import br.com.dbc.vemser.notifica.service.FeedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Feed Controller")
@RequestMapping("/feed")
public class FeedController implements IFeedController {
    private final FeedService feedService;

    @GetMapping
    public ResponseEntity<Page<FeedDenunciasDto>> listarTodasDenuncias(@PageableDefault(size = 5, page = 0) Pageable pageable) throws Exception{
        return new ResponseEntity<>(feedService.listarTodasDenuncias(pageable), HttpStatus.OK);
    }

    @GetMapping("/denuncias-com-comentarios")
    public ResponseEntity<Page<FeedDenunciasDto>> listarTodasDenunciasComComentarios(@PageableDefault(size = 10, page = 0) Pageable pageable) throws Exception{
        return new ResponseEntity<>(feedService.listarTodasDenunciasComComentarios(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idDenuncia}/com-comentarios")
    public ResponseEntity<DenunciaDTO> listarDenunciaComComentarios(@PathVariable("idDenuncia") Integer idDenuncia) throws Exception {
        DenunciaDTO denunciaDTOS = feedService.listarDenunciaComComentarios(idDenuncia);
        return ResponseEntity.ok(denunciaDTOS);
    }
}
