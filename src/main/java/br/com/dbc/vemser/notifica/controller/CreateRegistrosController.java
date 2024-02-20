package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.entity.CreateRegistros;
import br.com.dbc.vemser.notifica.service.CreateRegistrosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/create-registros")
@RequiredArgsConstructor
public class CreateRegistrosController {

    private final CreateRegistrosService createRegistrosService;


    @PostMapping("/insert")
    public String insertCreateRegistro() {
        createRegistrosService.insertCreateRegistro();
        return "Registro Criado!";
    }

    @GetMapping("/before-data")
    public List<CreateRegistros> findAllBeforeDate(@RequestParam String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dateTime, formatter);

        return createRegistrosService.findAllBeforeDate(data);
    }

    @GetMapping("/after-data")
    public List<CreateRegistros> findAllAfterDate(@RequestParam String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dateTime, formatter);
        return createRegistrosService.findAllAfterDate(data);
    }

    @GetMapping("/all")
    public List<CreateRegistros> findAll(){
        return createRegistrosService.findAll();
    }
}
