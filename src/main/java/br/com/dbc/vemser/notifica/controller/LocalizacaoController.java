package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoDto;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.repository.ILocalicacaoRepository;
import br.com.dbc.vemser.notifica.service.DenunciaService;
import br.com.dbc.vemser.notifica.service.LocalizacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "Localização Controller")
@RequestMapping("/localizacao")
public class LocalizacaoController {
    private final LocalizacaoService localizacaoService;

    @GetMapping
    public List<LocalizacaoDto> listarLocalizacao(){
        return localizacaoService.listarLocalizacao();
    }
}
