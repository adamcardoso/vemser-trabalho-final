package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IFeedController;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.service.DenunciaService;
import br.com.dbc.vemser.notifica.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController implements IFeedController {
    private final FeedService feedService;
    @GetMapping
    public ResponseEntity<List<DenunciaDTO>> listarTodasDenuncias() throws Exception {
        List<DenunciaDTO> denunciaDTOS = feedService.listarTodasDenuncias();
        return ResponseEntity.ok(denunciaDTOS);
    }
}
