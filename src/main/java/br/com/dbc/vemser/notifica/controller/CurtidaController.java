package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.dto.curtida.CurtidaDto;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.ICurtidaRepository;
import br.com.dbc.vemser.notifica.service.CurtidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apoiar")
public class CurtidaController {
    private final CurtidaService curtidaService;


    // receber id do comentário pelo body
    // receber id do usuário pelo body
    @PostMapping()
    public void apoiar(@RequestBody CurtidaDto curtidaDto) throws Exception {
        curtidaService.apoiar(curtidaDto);
    }
}
