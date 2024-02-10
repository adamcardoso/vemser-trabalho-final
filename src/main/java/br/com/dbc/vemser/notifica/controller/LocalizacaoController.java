package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoCreateDTO;
import br.com.dbc.vemser.notifica.service.LocalizacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "Localização Controller")
@RequestMapping("/localizacao")
public class LocalizacaoController {

    private final LocalizacaoService localizacaoService;

    @GetMapping
    public List<LocalizacaoCreateDTO> listarLocalizacao() {
        return localizacaoService.listarLocalizacao();
    }

    @GetMapping("/obterLinkGoogleMaps")
    public String obterLinkGoogleMaps(@RequestParam String latitude, @RequestParam String longitude) {
        return localizacaoService.obterLinkGoogleMaps(latitude, longitude);
    }
}
