package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.dto.estatistica.EstatisticaDTO;
import br.com.dbc.vemser.notifica.service.EstatisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {
    private final EstatisticaService estatisticaService;

//    @GetMapping("/bycolumn")
//    public ResponseEntity<HashMap<String, List<EstatisticaDTO>>> obterEstatistica(@RequestParam("coluna") List<String> colunas) {
//        Optional<HashMap<String, List<EstatisticaDTO>>> estatisticasOpt = estatisticaService.obterEstatistica(colunas);
//
//        return estatisticasOpt.map(estatisticas ->
//                                new ResponseEntity<>(estatisticas, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
//    }
}
