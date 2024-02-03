package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.ICurtidaController;
import br.com.dbc.vemser.notifica.dto.curtida.CurtidaDto;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.ICurtidaRepository;
import br.com.dbc.vemser.notifica.service.CurtidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apoiar")
public class CurtidaController implements ICurtidaController {
    private final CurtidaService curtidaService;

    @PostMapping()
    public void apoiar(@RequestBody CurtidaDto curtidaDto) throws Exception {
        curtidaService.apoiar(curtidaDto);
    }
}
