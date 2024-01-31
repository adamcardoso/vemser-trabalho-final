package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IDenunciaController;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.service.DenunciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/denuncia")
public class DenunciaController implements IDenunciaController{

    private final DenunciaService denunciaService;

    @GetMapping
    public ResponseEntity<List<DenunciaDTO>> listarTodasDenuncias() throws Exception {
        List<DenunciaDTO> denunciaDTOS = denunciaService.listarTodasDenuncias();
        return ResponseEntity.ok(denunciaDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Denuncia>> obterDenunciaById(@PathVariable("id") Integer id) throws Exception {
        Optional<Denuncia> denunciaDTO = denunciaService.obterDenunciaById(id);
        return ResponseEntity.ok(denunciaDTO);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<DenunciaDTO>> listByIdUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        List<DenunciaDTO> denunciaDTOS = denunciaService.listByIdUsuario(idUsuario);
        return ResponseEntity.ok(denunciaDTOS);
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
        return ResponseEntity.ok(deleted);
    }
}
