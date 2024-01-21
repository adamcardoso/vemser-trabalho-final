package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDto;
import br.com.dbc.vemser.notifica.dto.estatistica.EstatisticaDto;
import br.com.dbc.vemser.notifica.entity.Response;
import br.com.dbc.vemser.notifica.repository.EstatisticaRepository;
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
    public final EstatisticaService estatisticaService;

    @GetMapping("/bycolumn")
    public ResponseEntity<Response<HashMap<String, List<EstatisticaDto>>>> obterEstatistica(@RequestParam("coluna") List<String> colunas) throws Exception{
        try{
            return estatisticaService.obterEstatistica(colunas)
                    .map(c -> new ResponseEntity<>(
                            new Response<>(c, 200, "Estatística obtida com sucesso!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(
                            new Response<>(null, 404, "Houve algum problema"), HttpStatus.NOT_FOUND));
        } catch (Exception e){
            return new ResponseEntity<>(
                    new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}