package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.dto.estatistica.EstatisticaDTO;
import br.com.dbc.vemser.notifica.service.EstatisticaService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Estatísticas Controller")
@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {
    private final EstatisticaService estatisticaService;

    @GetMapping
    public ResponseEntity<EstatisticaDTO> obterEstatisticas() {
        EstatisticaDTO estatisticas = estatisticaService.gerarEstatisticas();
        return new ResponseEntity<>(estatisticas, HttpStatus.OK);
    }
}
