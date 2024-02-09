package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IDenunciaController;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.service.DenunciaService;
import br.com.dbc.vemser.notifica.service.LoginService;
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
public class DenunciaController{

    private final DenunciaService denunciaService;
    private final LoginService loginService;


    @GetMapping("/denuncia-por-id")
    public ResponseEntity<DenunciaDTO> obterDenunciaById() throws Exception {
        DenunciaDTO denunciaDTO = denunciaService.obterDenunciaById(loginService.getIdLoggedUser());
        return ResponseEntity.ok(denunciaDTO);
    }

    @GetMapping("/minhas-denuncias")
    public ResponseEntity<List<DenunciaDTO>> listByIdUsuario() throws Exception {
        List<DenunciaDTO> denunciaDTOS = denunciaService.listByIdUsuario(loginService.getIdLoggedUser());
        return ResponseEntity.ok(denunciaDTOS);
    }

    @PostMapping("/criar-denuncia")
    public ResponseEntity<DenunciaDTO> criarDenuncia(@Valid @RequestBody DenunciaCreateDTO denunciaCreateDTO) throws Exception {
        DenunciaDTO denunciaDTO = denunciaService.criarDenuncia(denunciaCreateDTO, loginService.getIdLoggedUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(denunciaDTO);
    }

    @PutMapping("/att-denuncia")
    public ResponseEntity<DenunciaDTO> editarDenuncia(@RequestParam Integer idDenuncia, @Valid @RequestBody DenunciaCreateDTO denunciaCreateDTO) throws Exception {
        DenunciaDTO denunciaDTO = denunciaService.editarDenuncia(denunciaCreateDTO, idDenuncia, loginService.getIdLoggedUser());
        return ResponseEntity.ok(denunciaDTO);
    }

    @DeleteMapping("/delete-denuncia/{idDenuncia}")
    public ResponseEntity<Object> deletarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia) throws Exception {
        denunciaService.deletarDenuncia(idDenuncia, loginService.getIdLoggedUser());
        return ResponseEntity.noContent().build();
    }
}
