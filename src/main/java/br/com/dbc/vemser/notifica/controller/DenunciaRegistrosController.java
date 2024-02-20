package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.entity.DenunciaRegistros;
import br.com.dbc.vemser.notifica.service.DenunciaRegistroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/denuncia-registros")
public class DenunciaRegistrosController {
    private final DenunciaRegistroService denunciaRegistroService;

    @PostMapping("/insert-inicio")
    public String insertCreateRegistroInicio() {
        denunciaRegistroService.inicioDenunciaRegistro();
        return "Registro Criado!";
    }

    @PostMapping("/insert-fechamento")
    public String insertCreateRegistrofechamento() {
        denunciaRegistroService.fechaDenunciaRegistro();
        return "Registro Criado!";
    }

    @GetMapping("/before-data-inicio")
    public List<DenunciaRegistros> findAllBeforeDateAndTipoRegistroInicio(@RequestParam String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dateTime, formatter);
        return denunciaRegistroService.findAllBeforeDateAndTipoRegistroInicio(data);
    }

    @GetMapping("/after-data-inicio")
    public List<DenunciaRegistros> findAllAfterDateTimeAndTipoRegistroInicio(@RequestParam String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dateTime, formatter);
        return denunciaRegistroService.findAllAfterDateTimeAndTipoRegistroInicio(data);
    }

    @GetMapping("/before-data-fechamento")
    public List<DenunciaRegistros> findAllBeforeDateAndTipoRegistroFechamento(@RequestParam String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dateTime, formatter);
        return denunciaRegistroService.findAllBeforeDateAndTipoRegistroFechamento(data);
    }
    @GetMapping("/after-data-fechamento")
    public List<DenunciaRegistros> findAllAfterDateTimeAndTipoRegistroFechamento(@RequestParam String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dateTime, formatter);
        return denunciaRegistroService.findAllAfterDateTimeAndTipoRegistroFechamento(data);
    }
    @GetMapping("/all")
    public List<DenunciaRegistros> findAll(){
        return denunciaRegistroService.findAll();
    }
}
