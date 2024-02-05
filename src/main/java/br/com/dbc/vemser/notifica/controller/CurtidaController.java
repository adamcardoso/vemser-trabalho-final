package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.ICurtidaController;
import br.com.dbc.vemser.notifica.dto.curtida.CurtidaDto;
import br.com.dbc.vemser.notifica.service.interfaces.ICurtidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apoiar")
public class CurtidaController implements ICurtidaController {
    private final ICurtidaService curtidaService;

    @Override
    @PostMapping("/comentario")
    public void apoiarComentario(@RequestBody CurtidaDto curtidaDto) throws Exception {
        curtidaService.apoiarComentario(curtidaDto);
    }

    @Override
    @PostMapping("/denuncia")
    public void apoiarDenuncia(@RequestBody CurtidaDto curtidaDto) throws Exception {
        curtidaService.apoiarDenuncia(curtidaDto);
    }
}
