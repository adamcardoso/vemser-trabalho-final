package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.service.CreateRegistrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/create-registros")
public class CreateRegistrosController {

    private final CreateRegistrosService createRegistrosService;

    @Autowired
    public CreateRegistrosController(CreateRegistrosService createRegistrosService) {
        this.createRegistrosService = createRegistrosService;
    }

    @PostMapping("/insert")
    public String insertCreateRegistro() {
        createRegistrosService.insertCreateRegistro();
        return "Registro Criado!";
    }
}
