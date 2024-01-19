package br.com.dbc.vemser.notifica.controller;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.service.DenunciaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.List;

@RestController
@RequestMapping("/denuncia") // localhost:8080/pessoa
public class DenunciaController {

    private DenunciaService denunciaService;

    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @GetMapping // GET localhost:8080/denuncia
    public List<Denuncia> list() {
        return denunciaService.list();
    }

    @GetMapping("/bytitulo") // GET localhost:8080/denuncia/bytitulo?titulo=Título da Denúncia 1
    public List<Denuncia> listByTitulo(@NotBlank @RequestParam("titulo") String titulo) {
        return denunciaService.listByTitulo(titulo);
    }

    @GetMapping("/{idUsuario}") // GET localhost:8080/denuncia/1
    public List<Denuncia> ListByIdUsuario(@PathVariable("idUsuario") String idUsuario) {
        return denunciaService.ListByIdUsuario(idUsuario);
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<Denuncia> criarDenuncia(@PathVariable("idUsuario") Integer idUsuario, @RequestBody Denuncia denuncia) throws RegraDeNegocioException {
        return ResponseEntity.ok(denunciaService.criarDenuncia(denuncia, idUsuario));
    }


}
