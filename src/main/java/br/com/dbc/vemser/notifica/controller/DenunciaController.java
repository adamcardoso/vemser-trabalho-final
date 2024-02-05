package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IDenunciaController;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaRelatorioDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioRelatorioDTO;
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

    @GetMapping("/relatorio")
    public ResponseEntity<List<DenunciaRelatorioDTO>> relatorioDenuncia() throws Exception {
        return new ResponseEntity<>(denunciaService.denunciaRelatorio(), HttpStatus.OK);
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<DenunciaDTO> criarDenuncia(@PathVariable("idUsuario") Integer idUsuario, @RequestBody DenunciaCreateDTO denunciaCreateDTO) throws Exception {
        DenunciaDTO denunciaDTO = denunciaService.criarDenuncia(denunciaCreateDTO, idUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(denunciaDTO);
    }

    @PutMapping("/{idDenuncia}/{idUsuario}")
    public ResponseEntity<DenunciaDTO> editarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia, @RequestBody DenunciaCreateDTO denunciaCreateDTO, @PathVariable("idUsuario") Integer idUsuario) throws Exception {
        DenunciaDTO denunciaDTO = denunciaService.editarDenuncia(denunciaCreateDTO, idDenuncia, idUsuario);
        return ResponseEntity.ok(denunciaDTO);
    }

    @DeleteMapping("/{idDenuncia}/{idUsuario}")
    public ResponseEntity<Object> deletarDenuncia(@PathVariable("idDenuncia") Integer idDenuncia, @PathVariable("idUsuario") Integer idUsuario) throws Exception {
        String deleted = denunciaService.deletarDenuncia(idDenuncia, idUsuario);
        return ResponseEntity.noContent().build();
    }
}
