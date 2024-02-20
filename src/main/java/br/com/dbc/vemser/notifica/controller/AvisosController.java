package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IAvisosController;
import br.com.dbc.vemser.notifica.dto.avisos.AvisosCreateDTO;
import br.com.dbc.vemser.notifica.dto.avisos.AvisosDTO;
import br.com.dbc.vemser.notifica.entity.Avisos;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.service.AvisosService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avisos")
@AllArgsConstructor
@Tag(name = "Instituição Avisos Controller")
public class AvisosController implements IAvisosController {

    private final AvisosService avisosService;

    @PostMapping
    public ResponseEntity<AvisosDTO> createAviso(@RequestBody AvisosCreateDTO avisoDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(avisosService.saveAviso(avisoDTO), HttpStatus.OK);
    }

}

