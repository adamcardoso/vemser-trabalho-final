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

    @PostMapping()
    public void apoiar(@RequestBody CurtidaDto curtidaDto) throws Exception {
        curtidaService.apoiar(curtidaDto);
    }
}
