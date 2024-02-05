package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IDenunciaController;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.service.DenunciaService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Denúncia Controller")
@RequestMapping("/denuncia")
public class DenunciaController implements IDenunciaController{

    private final DenunciaService denunciaService;

    @GetMapping("/{id}")
    public ResponseEntity<DenunciaDTO> obterDenunciaById(@PathVariable("id") Integer id) throws Exception {
        DenunciaDTO denunciaDTO = denunciaService.obterDenunciaById(id);
        return ResponseEntity.ok(denunciaDTO);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<DenunciaDTO>> listByIdUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        List<DenunciaDTO> denunciaDTOS = denunciaService.listByIdUsuario(idUsuario);
        return ResponseEntity.ok(denunciaDTOS);
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<DenunciaDTO> criarDenuncia(@PathVariable("idUsuario") Integer idUsuario, @Valid @RequestBody DenunciaCreateDTO denunciaCreateDTO) throws Exception {
        DenunciaDTO denunciaDTO = denunciaService.criarDenuncia(denunciaCreateDTO, idUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(denunciaDTO);
    }

    @PutMapping("/att-denuncia")
    public ResponseEntity<DenunciaDTO> editarDenuncia(@RequestParam Integer idDenuncia, @RequestParam("idUsuario") Integer idUsuario, @Valid @RequestBody DenunciaCreateDTO denunciaCreateDTO) throws Exception {
        DenunciaDTO denunciaDTO = denunciaService.editarDenuncia(denunciaCreateDTO, idDenuncia, idUsuario);
        return ResponseEntity.ok(denunciaDTO);
    }

    @DeleteMapping("/delete-denuncia")
    public ResponseEntity<Object> deletarDenuncia(@RequestParam Integer idDenuncia, @RequestParam Integer idUsuario) throws Exception {
        denunciaService.deletarDenuncia(idDenuncia, idUsuario);
        return ResponseEntity.noContent().build();
    }
}
